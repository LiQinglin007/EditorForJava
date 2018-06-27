package com.xiaomi.editor.controller;


import com.xiaomi.editor.bean.*;
import com.xiaomi.editor.service.*;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


/**
 * Description: 系统管理员<br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 8:45<br>
 * UpdateDescription：<br>
 */


@RequestMapping("system/admin")
@Controller
public class AdminController {

    @Resource
    private IStudioService mIStudioService;

    @Resource
    private IBannerService mIBannerService;

    @Resource
    private ICommodityService mICommodityService;

    @Resource
    private ISystemService mISystemService;

    @Resource
    private INoticeBeanService mINoticeBeanService;


    Logger logger = Logger.getLogger(AdminController.class);


    /**
     * 系统用户登录
     *
     * @param userLoginName
     * @param userPassword
     * @return
     */
    @RequestMapping("/systemLogin")
    @ResponseBody
    public ResponseJSON systemLogin(@RequestParam(value = "userLoginName") String userLoginName,
                                    @RequestParam(value = "userPassword") String userPassword) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("登录失败", null);
        //检查参数
        String checkStringList = CheckStringEmptyUtils.CheckStringList(
                new CheckStringEmptyUtils.CheckStringBean(userLoginName, "用户名不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(userPassword, "用户密码不能为空"));

        if (!checkStringList.equals(CheckStringEmptyUtils.ListSuccess)) {
            responseJSON.setMsg(checkStringList);
            return responseJSON;
        }

        SystemBean systemBean = mISystemService.queryByLoginName(userLoginName);
        if (systemBean == null) {
            responseJSON.setMsg("暂无此用户");
            return responseJSON;
        }

        if (!systemBean.getSystemUserPassword().equals(MD5Util.string2MD5(userPassword))) {
            responseJSON.setMsg("密码错误");
            return responseJSON;
        }

        // 判断redis是否有该用户 ,如果有则重新设置 失效时间
        List<String> liststr = JedisClientUtil.getAllKeys(FinalData.SYSTEM_TOKEN + "*");
        System.out.println(liststr);
        String token = "";
        if (liststr.size() > 0) {
            for (int i = 0; i < liststr.size(); i++) {
                String key = liststr.get(i);
                int uid = Integer.valueOf(JedisClientUtil.getString(key));
                if (systemBean.getSystemUserid() == uid) {
                    // 如果有则重新设置 失效时间,将原来的token返回
                    JedisClientUtil.setExpiryTime(key, FinalData.TOKEN_EXPIRY_SECONDS);
                    token = key.substring(FinalData.SYSTEM_TOKEN.length(), key.length());
                    break;
                }
            }
        }

        if (CheckStringEmptyUtils.IsEmpty(token)) {
            token = RandomUtils.getRandom(20, RandomUtils.NUMBER_LETTER);
            JedisClientUtil.saveString(FinalData.SYSTEM_TOKEN + token, systemBean.getSystemUserid() + "", FinalData.TOKEN_EXPIRY_SECONDS);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("systemUserId", systemBean.getSystemUserid());
        map.put("systemUserType", systemBean.getSystemUserType());
        map.put("token", token);

        ResponseUtils.getSuccessResponseBean("登录成功", map);

        return responseJSON;
    }

    /**
     * 检查当前用户是不是超级管理员
     *
     * @param request
     * @return
     */
    private boolean checkUser(HttpServletRequest request) {
        //通过请求头中的token拿到当前用户的userId
        String header = request.getHeader(FinalData.TOKENHEAD);
        String userId = JedisUtil.getSystemUserId(header);
        SystemBean systemBean = mISystemService.queryById(Integer.parseInt(userId));
        if (systemBean == null) {
            return false;
        }
        if (systemBean.getSystemUserType() == FinalData.SYSTEM_ADMIN) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改自己的密码
     *
     * @param userPassword
     * @return
     */
    @RequestMapping("/updateSystemPassword")
    @ResponseBody
    public ResponseJSON updateSystemPassword(HttpServletRequest request,
                                             @RequestParam(value = "userPassword") String userPassword) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("修改失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        String header = request.getHeader(FinalData.TOKENHEAD);
        int userId = Integer.parseInt(JedisUtil.getSystemUserId(header));
        SystemBean systemBean = new SystemBean();
        systemBean.setSystemUserid(userId);
        systemBean.setSystemUserPassword(MD5Util.string2MD5(userPassword));
        int i = mISystemService.updatePassword(systemBean);
        if (i != 0) {
            return ResponseUtils.getSuccessResponseBean("修改成功", null);
        }
        return responseJSON;
    }


    /**
     * 添加系统用户
     *
     * @param userLoginName 登录名称
     * @param userPassword  登录密码
     * @return
     */
    @RequestMapping("/addSysteamUser")
    @ResponseBody
    public ResponseJSON addSystemUser(HttpServletRequest request,
                                      @RequestParam(value = "userLoginName") String userLoginName,
                                      @RequestParam(value = "userPassword") String userPassword) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("添加失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        //检查参数
        String checkStringList = CheckStringEmptyUtils.CheckStringList(
                new CheckStringEmptyUtils.CheckStringBean(userLoginName, "用户名不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(userPassword, "用户密码不能为空"));
        if (!checkStringList.equals(CheckStringEmptyUtils.ListSuccess)) {
            responseJSON.setMsg(checkStringList);
            return responseJSON;
        }
        SystemBean systemBean = new SystemBean(userLoginName, MD5Util.string2MD5(userPassword));
        int i = mISystemService.addUser(systemBean);
        if (i > 0) {
            responseJSON = ResponseUtils.getSuccessResponseBean("添加成功", null);
        } else {
            responseJSON.setMsg("添加失败");
        }
        return responseJSON;
    }

    /**
     * 删除系统用户
     *
     * @param userId 用户id
     * @return
     */
    @RequestMapping("/delSystemUser")
    @ResponseBody
    public ResponseJSON delSystemUser(HttpServletRequest request, @RequestParam(value = "userId") int userId) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("删除失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        SystemBean systemBean = mISystemService.queryById(userId);
        if (systemBean == null) {
            responseJSON.setMsg("暂无该用户");
            return responseJSON;
        }
        int i = mISystemService.updateDelState(userId);
        if (i == 0) {
            responseJSON.setMsg("删除失败");
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("删除成功", null);
        //todo   这里要去关停工作室  关停工作室中的服务
        return responseJSON;
    }


    /**
     * 添加轮播图
     *
     * @param session
     * @param webUrl
     * @param weight
     * @param file
     * @return
     */
    @RequestMapping("/addBanner")
    @ResponseBody
    public ResponseJSON addBanner(HttpServletRequest request, HttpSession session,
                                  @RequestParam(value = "webUrl") String webUrl,
                                  @RequestParam(value = "weight") int weight,
                                  @RequestParam(value = "file") MultipartFile file) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("添加失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }

        if (file == null) {
            responseJSON.setMsg("图片不能为空");
            return responseJSON;
        }
        String saveFile = "";
        try {
            saveFile = FileUtil.saveFile(session, file);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("addBanner文件保存失败：" + e.toString());
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }

        int i = mIBannerService.addBanner(new BannerBean(saveFile, Short.parseShort(weight + ""), webUrl));
        if (i == 0) {
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("添加成功", null);
        return responseJSON;
    }

    /**
     * 删除轮播图
     *
     * @param bannerId
     * @return
     */
    @RequestMapping("/delBanner")
    @ResponseBody
    public ResponseJSON delBanner(HttpServletRequest request, @RequestParam(value = "bannerId") int bannerId) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("删除失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        int i = mIBannerService.updateDelState(bannerId);
        if (i == 0) {
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("删除成功", null);
        return responseJSON;
    }


    /**
     * 修改Banner
     *
     * @param bannerId
     * @return
     */
    @RequestMapping("/updateBanner")
    @ResponseBody
    public ResponseJSON updateBanner(HttpServletRequest request, HttpSession session,
                                     @RequestParam(value = "bannerId") int bannerId,
                                     @RequestParam(value = "webUrl") String webUrl,
                                     @RequestParam(value = "weight") int weight,
                                     @RequestParam(value = "file", required = false) MultipartFile file) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("添加失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        String saveFile = "";
        if (file != null) {
            try {
                saveFile = FileUtil.saveFile(session, file);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("updateBanner文件保存失败：" + e.toString());
                responseJSON.setMsg("添加失败");
                return responseJSON;
            }
        }
        int i = mIBannerService.updateBanner(new BannerBean(bannerId, saveFile, Short.parseShort(weight + ""), webUrl));
        if (i == 0) {
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("修改成功", null);
        return responseJSON;
    }


    /**
     * 发布公告
     *
     * @param noticeTitle   公告标题
     * @param noticeContent 公告内容
     * @return
     */
    @RequestMapping("/addNotice")
    @ResponseBody
    public ResponseJSON addNotice(HttpServletRequest request,
                                  @RequestParam(value = "noticeTitle") String noticeTitle,
                                  @RequestParam(value = "noticeContent") String noticeContent) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("发布失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        String checkStringList = CheckStringEmptyUtils.CheckStringList(new CheckStringEmptyUtils.CheckStringBean(noticeTitle, "公告标题不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(noticeContent, "公告内容不能为空"));
        if (!checkStringList.equals(CheckStringEmptyUtils.ListSuccess)) {
            responseJSON.setMsg(checkStringList);
            return responseJSON;
        }
        int i = mINoticeBeanService.addNotice(new NoticeBean(noticeTitle, noticeContent, new Date()));
        if (i == 0) {
            responseJSON.setMsg("发布失败");
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("发布成功", null);
        return responseJSON;
    }


    /**
     * 删除公告
     *
     * @param noticeId
     * @return
     */
    @RequestMapping("/delNotice")
    @ResponseBody
    public ResponseJSON delNotice(HttpServletRequest request, @RequestParam(value = "noticeId") int noticeId) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("发布失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        NoticeBean noticeBean = mINoticeBeanService.queryById(noticeId);
        if (noticeBean == null) {
            responseJSON.setMsg("暂无当前公告");
            return responseJSON;
        }
        int i = mINoticeBeanService.updateDelState(noticeId);
        if (i == 0) {
            responseJSON.setMsg("删除失败");
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("删除成功", null);
        return responseJSON;
    }


    /**
     * 添加工作室
     *
     * @param studioName              工作室名称
     * @param studioMoney             工作室押金
     * @param studioPhone             联系电话
     * @param studioQQ                工作QQ
     * @param studioBriefintroduction 简介
     * @return
     */
    @RequestMapping("/addStudio")
    @ResponseBody
    public ResponseJSON addStudio(HttpServletRequest request, HttpSession session,
                                  @RequestParam(value = "studioName") String studioName,
                                  @RequestParam(value = "studioMoney") Float studioMoney,
                                  @RequestParam(value = "studioPhone") String studioPhone,
                                  @RequestParam(value = "studioQQ") String studioQQ,
                                  @RequestParam(value = "studioBriefintroduction") String studioBriefintroduction,
                                  @RequestParam(value = "file") MultipartFile file
    ) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("添加失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        //检查参数
        String checkStringList = CheckStringEmptyUtils.CheckStringList(new CheckStringEmptyUtils.CheckStringBean(studioName, "工作室名称不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(studioPhone, "工作室联系电话不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(studioQQ, "工作QQ不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(studioBriefintroduction, "工作室简介不能为空"));
        if (!checkStringList.equals(CheckStringEmptyUtils.ListSuccess)) {
            responseJSON.setMsg(checkStringList);
            return responseJSON;
        }
        //检查押金
        if (studioMoney < 0) {
            responseJSON.setMsg("押金不能小于0");
            return responseJSON;
        }
        //检查图片
        if (file == null) {
            responseJSON.setMsg("工作室图片不能为空");
            return responseJSON;
        }
        //保存图片
        String studioPic = "";
        try {
            studioPic = FileUtil.saveFile(session, file);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("addStudio文件保存失败：" + e.toString());
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }
        //插入数据库
        StudioBean mStudioBean = new StudioBean(studioName, studioPic, studioMoney, studioPhone, studioQQ, studioBriefintroduction);
        int i = mIStudioService.addStudio(mStudioBean);
        if (i < 0) {
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("添加成功", null);
        return responseJSON;
    }


    /**
     * 删除工作室
     *
     * @param studioId
     * @return
     */
    @RequestMapping("/delStudio")
    @ResponseBody
    public ResponseJSON delStudio(HttpServletRequest request, @RequestParam(value = "studioId") int studioId) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("添加失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        StudioBean studioBean = mIStudioService.queryById(studioId);
        if (studioBean == null) {
            responseJSON.setMsg("暂无该工作室");
            return responseJSON;
        }
        int i = mIStudioService.updateDelState(studioId);
        if (i == 0) {
            responseJSON.setMsg("删除失败");
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("删除成功", null);
        return responseJSON;
    }


    /**
     * 设置热门服务
     *
     * @param CommodityId  商品id
     * @param HotCommodity 1:设置热门服务  0:取消设置
     * @return
     */
    @RequestMapping("/setHotCommodity")
    @ResponseBody
    public ResponseJSON updateBanner(HttpServletRequest request, @RequestParam(value = "CommodityId") int CommodityId,
                                     @RequestParam(value = "HotCommodity") int HotCommodity) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("添加失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        CommodityBean commodityBean = mICommodityService.queryById(CommodityId);
        if (commodityBean == null) {
            responseJSON.setMsg("暂无当前服务");
            return responseJSON;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("CommodityId", CommodityId);
        map.put("HotCommodity", HotCommodity);
        int i = mICommodityService.updateHotState(map);

        if (i == 0) {
            responseJSON.setMsg("设置失败");
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("修改成功", null);
        return responseJSON;
    }

    public static void main(String[] args) {
        String uId = JedisClientUtil.getString("");
        System.out.println("uId:"+uId);
    }
}
