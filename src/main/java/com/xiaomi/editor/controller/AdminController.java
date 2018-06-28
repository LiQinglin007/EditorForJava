package com.xiaomi.editor.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.xiaomi.editor.bean.*;
import com.xiaomi.editor.dao.NoticeBeanMapper;
import com.xiaomi.editor.service.*;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/systemLogin", method = RequestMethod.POST)
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


//=============================================================↓↓↓↓↓↓↓↓↓系统用户模块↓↓↓↓↓==========================================================

    /**
     * 添加工作室用户
     *
     * @param userLoginName 登录名称
     * @param userPassword  登录密码
     * @return
     */
    @RequestMapping(value = "/addSysteamUser", method = RequestMethod.POST)
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

        SystemBean systemBean1 = mISystemService.queryByLoginName(userLoginName);
        if (systemBean1 != null) {
            responseJSON.setMsg("已有当前用户名");
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
     * 修改自己的密码
     *
     * @param userPassword
     * @return
     */
    @RequestMapping(value = "/updateSystemPassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON updateSystemPassword(HttpServletRequest request,
                                             @RequestParam(value = "userPassword") String userPassword) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("修改失败", null);
        String header = request.getHeader(FinalData.TOKENHEAD);
        int userId = Integer.parseInt(JedisUtil.getSystemUserId(header));
        SystemBean systemBean1 = mISystemService.queryById(userId);
        if (systemBean1 == null) {
            responseJSON.setMsg("暂无该用户");
            return responseJSON;
        }
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
     * 获取全部系统用户(工作室用户)
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getSysteamUserList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON getSysteamUserList(HttpServletRequest request,
                                           @RequestParam(value = "page") int page,
                                           @RequestParam(value = "size") int size) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("查询失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        PageInfo noticeBeanPageInfo = mISystemService.selectByPage(page, size);
        responseJSON = ResponseUtils.getSuccessResponseBean("查询成功", noticeBeanPageInfo);
        return responseJSON;
    }


    /**
     * 删除系统用户
     *
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/delSystemUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON delSystemUser(HttpServletRequest request,
                                      @RequestParam(value = "userId") int userId) {
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


//=============================================================↑↑↑↑↑↑↑系统用户模块↑↑↑↑↑↑↑==========================================================


//=============================================================↓↓↓↓↓↓↓↓↓轮播图模块↓↓↓↓↓==========================================================

    /**
     * 添加轮播图
     *
     * @param session
     * @param webUrl
     * @param weight
     * @param file
     * @return
     */
    @RequestMapping(value = "/addBanner", method = RequestMethod.POST)
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
    @RequestMapping(value = "/delBanner", method = RequestMethod.POST)
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
    @RequestMapping(value = "/updateBanner", method = RequestMethod.POST)
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
     * 获取轮播图列表
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getBannerList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON getBannerList(HttpServletRequest request, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("查询失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        PageInfo noticeBeanPageInfo = mIBannerService.selectByPage(page, size);
        responseJSON = ResponseUtils.getSuccessResponseBean("查询成功", noticeBeanPageInfo);
        return responseJSON;
    }


//=============================================================↑↑↑↑↑↑↑轮播图模块↑↑↑↑↑↑↑==========================================================


//=============================================================↓↓↓↓↓↓↓公告模块↓↓↓↓↓↓↓==========================================================

    /**
     * 发布公告
     *
     * @param noticeTitle   公告标题
     * @param noticeContent 公告内容
     * @return
     */
    @RequestMapping(value = "/addNotice", method = RequestMethod.POST)
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
    @RequestMapping(value = "/delNotice", method = RequestMethod.POST)
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
     * 获取公告列表
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getNoticeList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON getNoticeList(HttpServletRequest request, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("查询失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        PageInfo noticeBeanPageInfo = mINoticeBeanService.selectByPage(page, size);
        responseJSON = ResponseUtils.getSuccessResponseBean("查询成功", noticeBeanPageInfo);
        return responseJSON;
    }


//=============================================================↑↑↑↑↑↑↑公告模块↑↑↑↑↑↑↑==========================================================


//=============================================================↓↓↓↓↓↓工作室模块↓↓↓↓↓↓↓==========================================================

    /**
     * 添加工作室
     *
     * @param studioUserId            系统用户id
     * @param studioName              工作室名称
     * @param studioMoney             工作室押金
     * @param studioPhone             联系电话
     * @param studioQQ                工作QQ
     * @param studioBriefintroduction 简介
     * @return
     */
    @RequestMapping(value = "/addStudio", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON addStudio(HttpServletRequest request, HttpSession session,
                                  @RequestParam(value = "studioUserId") int studioUserId,
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
        String studioNamePin = "";
        try {
            studioNamePin = PinyinUtil.getPinyin(studioName);
        } catch (PinyinException e) {
            e.printStackTrace();
            logger.error("addStudio汉字转换拼音失败：" + e.toString());
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }
        //插入数据库
        StudioBean mStudioBean = new StudioBean(studioName, studioNamePin, studioPic, studioMoney, studioPhone, studioQQ, studioBriefintroduction, studioUserId);
        int i = mIStudioService.addStudio(mStudioBean);
        if (i < 0) {
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("添加成功", null);
        return responseJSON;
    }


    /**
     * 获取工作室详情
     *
     * @param studioId 工作室id
     * @return
     */
    @RequestMapping(value = "/getStudioData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON getStudioData(@RequestParam(value = "studioId") int studioId) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("获取失败");
        StudioBean studioBean = mIStudioService.queryById(studioId);
        if (studioBean == null) {
            responseJSON.setMsg("暂无此工作室");
            return responseJSON;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("studioName", studioBean.getStudioName());
        map.put("systemUserid", studioBean.getSystemUserid());
        map.put("studioCollectionNmuber", studioBean.getStudioCollectionNmuber());
        map.put("studioBriefintroduction", studioBean.getStudioBriefintroduction());
        map.put("studioMonthlySales", studioBean.getStudioMonthlySales());
        map.put("studioQq", studioBean.getStudioQq());
        map.put("studioPhone", studioBean.getStudioPhone());
        map.put("studioMoney", studioBean.getStudioMoney());
        map.put("studioPic", studioBean.getStudioPic());
        responseJSON = ResponseUtils.getSuccessResponseBean("获取成功", map);
        return responseJSON;
    }


    /**
     * 修改工作室信息
     *
     * @param request
     * @param session
     * @param studioId                工作室id
     * @param studioUserId            系统用户id
     * @param studioName              工作室名称
     * @param studioMoney             押金
     * @param studioPhone             联系电话
     * @param studioQQ                QQ
     * @param studioBriefintroduction 简介
     * @param file                    图片
     * @return
     */
    @RequestMapping(value = "/updateStudioData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON updateStudioData(HttpServletRequest request, HttpSession session,
                                         @RequestParam(value = "studioId") int studioId,
                                         @RequestParam(value = "studioUserId") int studioUserId,
                                         @RequestParam(value = "studioName") String studioName,
                                         @RequestParam(value = "studioMoney") Float studioMoney,
                                         @RequestParam(value = "studioPhone") String studioPhone,
                                         @RequestParam(value = "studioQQ") String studioQQ,
                                         @RequestParam(value = "studioBriefintroduction") String studioBriefintroduction,
                                         @RequestParam(value = "file") MultipartFile file) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("获取失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        StudioBean studioBean = mIStudioService.queryById(studioId);
        if (studioBean == null) {
            responseJSON.setMsg("暂无此工作室");
            return responseJSON;
        }
        String saveUrl = "";
        if (file != null) {
            try {
                saveUrl = FileUtil.saveFile(session, file);
                studioBean.setStudioPic(saveUrl);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("updateStudioData文件保存失败：" + e.toString());
                responseJSON.setMsg("修改失败");
                return responseJSON;
            }
        }
        studioBean.setSystemUserid(studioUserId);//用户id
        studioBean.setStudioName(studioName);//店铺名称
        studioBean.setStudioMoney(studioMoney);//押金
        studioBean.setStudioPhone(studioPhone);//电话
        studioBean.setStudioQq(studioQQ);//QQ
        studioBean.setStudioBriefintroduction(studioBriefintroduction);//简介
        int i = mIStudioService.updateStudioByAdmin(studioBean);
        if (i == 0) {
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("修改成功");
        return responseJSON;
    }


    /**
     * 删除工作室
     *
     * @param studioId
     * @return
     */
    @RequestMapping(value = "/delStudio", method = RequestMethod.POST)
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
     * 获取全部工作室
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getStudioList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON getStudioList(HttpServletRequest request, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("查询失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        PageInfo studioBeanPageInfo = mIStudioService.selectByPage(page, size);
        responseJSON = ResponseUtils.getSuccessResponseBean("查询成功", studioBeanPageInfo);
        return responseJSON;
    }


    /**
     * 查询工作室(按工作室名称模糊查询)
     *
     * @param request
     * @param searchContent 查询内容
     * @return
     */
    @RequestMapping(value = "/getStudioByStudioNamePin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON getStudioByStudioNamePin(HttpServletRequest request,
                                                 @RequestParam(value = "searchContent") String searchContent) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("查询失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        String searchContentPin = "";
        try {
            searchContentPin = PinyinUtil.getPinyin(searchContent);
        } catch (PinyinException e) {
            e.printStackTrace();
            logger.error("getStudioByStudioNamePin汉字转换拼音失败：" + e.toString());
            return responseJSON;
        }
        List studioBeanList = mIStudioService.selectByStudioBeanNamePin(searchContentPin);
        responseJSON = ResponseUtils.getSuccessResponseBean("查询成功", studioBeanList);
        return responseJSON;
    }


//=============================================================↑↑↑↑↑↑工作室模块↑↑↑↑↑↑↑==========================================================


//=============================================================↓↓↓↓↓↓热门服务模块↓↓↓↓↓↓↓==========================================================

    /**
     * 设置热门服务
     *
     * @param CommodityId  商品id
     * @param HotCommodity 1:设置热门服务  0:取消设置
     * @return
     */
    @RequestMapping(value = "/setHotCommodity", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON setHotCommodity(HttpServletRequest request, @RequestParam(value = "CommodityId") int CommodityId,
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


    /**
     * 获取工作室的全部商品
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getCommodityListByStudioId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON getCommodityListByStudioId(HttpServletRequest request, @RequestParam(value = "studioId") int studioId,
                                                   @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("查询失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        PageInfo studioBeanPageInfo = mICommodityService.selectByPage(studioId, page, size);
        responseJSON = ResponseUtils.getSuccessResponseBean("查询成功", studioBeanPageInfo);
        return responseJSON;
    }


    /**
     * 获取工作室中全部非热门商品
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getNotHotCommodityListByStudioId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON getNotHotCommodityListByStudioId(HttpServletRequest request, @RequestParam(value = "studioId") int studioId,
                                                         @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("查询失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        PageInfo studioBeanPageInfo = mICommodityService.selectNotHotCommodityByPage(studioId, page, size);
        responseJSON = ResponseUtils.getSuccessResponseBean("查询成功", studioBeanPageInfo);
        return responseJSON;
    }


    /**
     * 获取全部热门商品
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getHotCommodityList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON getHotCommodityList(HttpServletRequest request,
                                            @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("查询失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        PageInfo studioBeanPageInfo = mICommodityService.selectHotCommodityByPage(page, size);
        responseJSON = ResponseUtils.getSuccessResponseBean("查询成功", studioBeanPageInfo);
        return responseJSON;
    }


//=============================================================↑↑↑↑↑↑热门服务模块↑↑↑↑↑↑↑==========================================================


}
