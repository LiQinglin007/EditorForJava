package com.xiaomi.editor.controller;

import com.xiaomi.editor.bean.CommodityBean;
import com.xiaomi.editor.bean.StudioBean;
import com.xiaomi.editor.bean.SystemBean;
import com.xiaomi.editor.service.ICommodityService;
import com.xiaomi.editor.service.IStudioService;
import com.xiaomi.editor.service.ISystemService;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Description: 工作室<br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-26<br>
 * Time: 8:33<br>
 * UpdateDescription：<br>
 */
@RequestMapping("system/studio")
@Controller
public class SystemController {
    @Resource
    private IStudioService studioService;
    @Resource
    private ICommodityService commodityService;
    @Resource
    private ISystemService mISystemService;

    Logger logger = Logger.getLogger(SystemController.class);

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
        if (systemBean.getSystemUserType() == FinalData.SYSTEM_STUDIO) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改自己工作室数据
     *
     * @param session
     * @param studioId                工作室id
     * @param studioName              工作室名称
     * @param phone                   联系电话
     * @param qq                      联系QQ
     * @param studioBriefintroduction 商铺简介
     * @param file                    商铺头像
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStudioData")
    public ResponseJSON updateStudioData(HttpServletRequest request, HttpSession session, @RequestParam int studioId,
                                         @RequestParam String studioName, @RequestParam String phone,
                                         @RequestParam String qq, @RequestParam String studioBriefintroduction,
                                         @RequestParam(value = "file") MultipartFile file) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("修改失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        String checkStringList = CheckStringEmptyUtils.CheckStringList(
                new CheckStringEmptyUtils.CheckStringBean(studioName, "工作室名称不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(phone, "工作室联系电话不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(qq, "工作QQ不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(studioBriefintroduction, "工作室简介不能为空"));
        if (!checkStringList.equals(CheckStringEmptyUtils.ListSuccess)) {
            responseJSON.setMsg(checkStringList);
            return responseJSON;
        }
        //保存图片
        String studioPic = "";
        if (file != null) {
            try {
                studioPic = FileUtil.saveFile(session, file);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("updateStudioData文件保存失败：" + e.toString());
                responseJSON.setMsg("添加失败");
                return responseJSON;
            }
        }
        StudioBean mStudioBean = new StudioBean(studioId, studioName, studioPic, phone, qq, studioBriefintroduction);
        int i = studioService.updateStudio(mStudioBean);
        if (i == 0) {
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("修改成功", null);
        return responseJSON;
    }

    /**
     * 添加商品
     *
     * @param session
     * @param studioId              工作室id
     * @param commodityName         商品名称
     * @param commodityPresentPrice 商品价格
     * @param commodityType         商品类型   (1:查重、2:降重、3:速审)
     * @param file                  商品头像
     * @param files                 商品详情图片
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCommodity")
    public ResponseJSON addCommodity(HttpServletRequest request, HttpSession session, @RequestParam int studioId,
                                     @RequestParam String commodityName, @RequestParam Float commodityPresentPrice,
                                     @RequestParam int commodityType,
                                     @RequestParam(value = "picFile") MultipartFile file,
                                     @RequestParam(value = "picsFile") MultipartFile[] files) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("添加失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        if (CheckStringEmptyUtils.IsEmpty(commodityName)) {
            responseJSON.setMsg("服务名称不能为空");
            return responseJSON;
        }

        if (file == null) {
            responseJSON.setMsg("服务图片不能为空");
            return responseJSON;
        }

        if (files == null || files.length == 0) {
            responseJSON.setMsg("服务详情图片不能为空");
            return responseJSON;
        }

        String picUrl = "";
        try {
            picUrl = FileUtil.saveFile(session, file);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("addCommodity文件保存失败：" + e.toString());
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }

        String picsUrl = "";
        try {
            picsUrl = FileUtil.saveFile(session, files);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("addCommodity服务详情文件保存失败：" + e.toString());
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }

        CommodityBean commodityBean = new CommodityBean(studioId, commodityName, picUrl, picsUrl,
                commodityPresentPrice, Short.parseShort(commodityType + ""));
        int i = commodityService.addCommodity(commodityBean);
        if (i == 0) {
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("添加成功");
        return responseJSON;
    }

    /**
     * 修改商品
     *
     * @param session
     * @param commodityId           商品id
     * @param commodityName         商品名称
     * @param commodityPresentPrice 商品价格
     * @param commodityType         商品类型
     * @param file                  图片
     * @param files                 详情图片
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateCommodity")
    public ResponseJSON updateCommodity(HttpServletRequest request, HttpSession session, @RequestParam int commodityId,
                                        @RequestParam String commodityName, @RequestParam Float commodityPresentPrice,
                                        @RequestParam int commodityType,
                                        @RequestParam(value = "picFile") MultipartFile file,
                                        @RequestParam(value = "picsFile") MultipartFile[] files) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("修改失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        String picUrl = "";
        if (file != null) {
            try {
                picUrl = FileUtil.saveFile(session, file);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("addCommodity文件保存失败：" + e.toString());
                responseJSON.setMsg("修改失败");
                return responseJSON;
            }
        }

        String picsUrl = "";
        if (files != null && files.length != 0) {
            try {
                picsUrl = FileUtil.saveFile(session, files);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("addCommodity服务详情文件保存失败：" + e.toString());
                responseJSON.setMsg("修改失败");
                return responseJSON;
            }
        }

        CommodityBean commodityBean = commodityService.queryById(commodityId);
        if (commodityBean == null) {
            responseJSON.setMsg("暂无该商品");
            return responseJSON;
        }
        //如果价格变化了,把之前的现价放在原价里边
        if (commodityPresentPrice != commodityBean.getCommodityPresentPrice()) {
            commodityBean.setCommodityOriginalPrice(commodityBean.getCommodityPresentPrice());
        }
        //如果商品名变化
        if (!CheckStringEmptyUtils.IsEmpty(commodityName)) {
            commodityBean.setCommodityName(commodityName);
        }
        //如果服务类型变化
        if (commodityType != commodityBean.getCommodityType()) {
            commodityBean.setCommodityType(Short.parseShort(commodityType + ""));
        }

        if (!CheckStringEmptyUtils.IsEmpty(picUrl)) {
            commodityBean.setCommodityPic(picUrl);
        }
        if (!CheckStringEmptyUtils.IsEmpty(picsUrl)) {
            commodityBean.setCommodityPics(picsUrl);
        }

        int i = commodityService.updateCommodity(commodityBean);
        if (i == 0) {
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("修改成功");
        return responseJSON;
    }

    /**
     * 下架商品
     *
     * @param commodityId 商品id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delCommodity")
    public ResponseJSON delCommodity(HttpServletRequest request, @RequestParam int commodityId) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("下架失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        CommodityBean commodityBean = commodityService.queryById(commodityId);
        if (commodityBean == null) {
            responseJSON.setMsg("暂无该商品");
            return responseJSON;
        }
        int i = commodityService.updateCommodityDel(commodityId);
        if (i == 0) {
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("下架成功");
        return responseJSON;
    }


    //订单管理
    //查看对工作室的评价
    //查看对商品的评价
}
