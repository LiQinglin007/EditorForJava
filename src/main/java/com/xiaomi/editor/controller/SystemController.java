package com.xiaomi.editor.controller;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.xiaomi.editor.bean.CommodityBean;
import com.xiaomi.editor.bean.StudioBean;
import com.xiaomi.editor.bean.SystemBean;
import com.xiaomi.editor.paramsbean.Commodity;
import com.xiaomi.editor.paramsbean.Studio;
import com.xiaomi.editor.service.ICommodityService;
import com.xiaomi.editor.service.IStudioService;
import com.xiaomi.editor.service.ISystemService;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     * 检查当前用户是不是工作室管理员
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
     * @param studio  工作室id,工作室名称,联系电话,联系QQ,商铺简介,商铺头像
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateStudioData", method = RequestMethod.POST)
    public ResponseJSON updateStudioData(HttpServletRequest request, HttpSession session,
                                         @RequestBody Studio studio
    ) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("修改失败", null);
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        String checkStringList = CheckStringEmptyUtils.CheckStringList(
                new CheckStringEmptyUtils.CheckStringBean(studio.getStudioName(), "工作室名称不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(studio.getStudioPhone(), "工作室联系电话不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(studio.getStudioQq(), "工作QQ不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(studio.getStudioBriefintroduction(), "工作室简介不能为空"));
        if (!checkStringList.equals(CheckStringEmptyUtils.ListSuccess)) {
            responseJSON.setMsg(checkStringList);
            return responseJSON;
        }
        //保存图片
        String studioPic = "";
        if (studio.getFile() != null) {
            try {
                studioPic = FileUtil.saveFile(session, studio.getFile());
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("updateStudioData文件保存失败：" + e.toString());
                responseJSON.setMsg("添加失败");
                return responseJSON;
            }
        }
        String studioNamePin = "";
        try {
            studioNamePin = PinyinUtil.getPinyin(studio.getStudioName());
        } catch (PinyinException e) {
            e.printStackTrace();
            logger.error("updateStudioData汉字转换拼音失败：" + e.toString());
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }
        StudioBean mStudioBean = new StudioBean(studio.getStudioId(), studio.getStudioName(), studioNamePin, studioPic, studio.getStudioPhone(), studio.getStudioQq(),
                studio.getStudioBriefintroduction());
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
     * @param commodity 工作室id,商品名称,商品介绍,商品价格,商品类型   (1:查重、2:降重、3:速审),商品头像,商品详情图片
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCommodity", method = RequestMethod.POST)
    public ResponseJSON addCommodity(HttpServletRequest request, HttpSession session, @RequestBody Commodity commodity) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("添加失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        if (CheckStringEmptyUtils.IsEmpty(commodity.getCommodityName())) {
            responseJSON.setMsg("服务名称不能为空");
            return responseJSON;
        }
        if (CheckStringEmptyUtils.IsEmpty(commodity.getCommodityIntroduce())) {
            responseJSON.setMsg("服务简介不能为空");
            return responseJSON;
        }

        if (commodity.getFile() == null) {
            responseJSON.setMsg("服务图片不能为空");
            return responseJSON;
        }

        if (commodity.getFiles() == null || commodity.getFiles().length == 0) {
            responseJSON.setMsg("服务详情图片不能为空");
            return responseJSON;
        }

        String picUrl = "";
        try {
            picUrl = FileUtil.saveFile(session, commodity.getFile());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("addCommodity文件保存失败：" + e.toString());
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }

        String picsUrl = "";
        try {
            picsUrl = FileUtil.saveFile(session, commodity.getFiles());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("addCommodity服务详情文件保存失败：" + e.toString());
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }
        String commodityNamePin = "";
        try {
            commodityNamePin = PinyinUtil.getPinyin(commodity.getCommodityName());
        } catch (PinyinException e) {
            e.printStackTrace();
            logger.error("addCommodity汉字转换拼音失败：" + e.toString());
            responseJSON.setMsg("添加失败");
            return responseJSON;
        }

        CommodityBean commodityBean = new CommodityBean(commodity.getCommodityId(), commodity.getCommodityName(), commodityNamePin,
                commodity.getCommodityIntroduce(), picUrl, picsUrl, commodity.getCommodityPresentPrice(), commodity.getCommodityType()
        );
        int i = commodityService.addCommodity(commodityBean);
        if (i == 0) {
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("添加成功");
        return responseJSON;
    }


    /**
     * 查询某个商品的详情
     *
     * @param request
     * @param commodity id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCommodityById", method = RequestMethod.POST)
    public ResponseJSON queryCommodityById(HttpServletRequest request, @RequestBody Commodity commodity) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("查询失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        CommodityBean commodityBean = commodityService.queryById(commodity.getCommodityId());
        if (commodityBean == null) {
            responseJSON.setMsg("暂无该商品");
            return responseJSON;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("commodity_name", commodityBean.getCommodityName());
        map.put("commodity_present_price", commodityBean.getCommodityPresentPrice());
        map.put("studio_id", commodityBean.getStudioId());
        map.put("commodity_type", commodityBean.getCommodityType());
        map.put("commodity_name", commodityBean.getCommodityName());
        map.put("commodity_pics", commodityBean.getCommodityPics());
        map.put("commodity_pic", commodityBean.getCommodityPic());
        map.put("commodity_hot", commodityBean.getCommodityHot());
        map.put("commodity_introduce", commodityBean.getCommodityIntroduce());
        map.put("commodity_id", commodityBean.getCommodityId());
        map.put("commodity_collection_quantity", commodityBean.getCommodityCollectionQuantity());
        map.put("commodity_original_price", commodityBean.getCommodityOriginalPrice());
        responseJSON = ResponseUtils.getSuccessResponseBean("查询成功", map);
        return responseJSON;
    }

    /**
     * 修改商品
     *
     * @param session
     * @param commodity 商品id,商品名称,商品价格,商品类型,图片,详情图片
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateCommodity", method = RequestMethod.POST)
    public ResponseJSON updateCommodity(HttpServletRequest request, HttpSession session, @RequestBody Commodity commodity) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("修改失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        String picUrl = "";
        if (commodity.getFile() != null) {
            try {
                picUrl = FileUtil.saveFile(session, commodity.getFile());
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("addCommodity文件保存失败：" + e.toString());
                responseJSON.setMsg("修改失败");
                return responseJSON;
            }
        }

        String picsUrl = "";
        if (commodity.getFiles() != null && commodity.getFiles().length != 0) {
            try {
                picsUrl = FileUtil.saveFile(session, commodity.getFiles());
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("addCommodity服务详情文件保存失败：" + e.toString());
                responseJSON.setMsg("修改失败");
                return responseJSON;
            }
        }

        CommodityBean commodityBean = commodityService.queryById(commodity.getCommodityId());
        if (commodityBean == null) {
            responseJSON.setMsg("暂无该商品");
            return responseJSON;
        }
        //如果价格变化了,把之前的现价放在原价里边
        if (commodity.getCommodityPresentPrice() != commodityBean.getCommodityPresentPrice()) {
            commodityBean.setCommodityOriginalPrice(commodityBean.getCommodityPresentPrice());
        }
        //如果商品名变化
        if (!CheckStringEmptyUtils.IsEmpty(commodity.getCommodityName())) {
            commodityBean.setCommodityName(commodity.getCommodityName());
            String commodityNamePin = "";
            try {
                commodityNamePin = PinyinUtil.getPinyin(commodity.getCommodityName());
            } catch (PinyinException e) {
                e.printStackTrace();
                logger.error("addCommodity汉字转换拼音失败：" + e.toString());
                responseJSON.setMsg("修改失败");
                return responseJSON;
            }
            commodityBean.setCommodityNamePin(commodityNamePin);
        }

        //商品简介变化
        if (!CheckStringEmptyUtils.IsEmpty(commodity.getCommodityIntroduce())) {
            commodityBean.setCommodityIntroduce(commodity.getCommodityIntroduce());
        }
        //如果服务类型变化
        if (commodity.getCommodityType() != commodityBean.getCommodityType()) {
            commodityBean.setCommodityType(commodity.getCommodityType());
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
     * @param commodity 商品id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delCommodity", method = RequestMethod.POST)
    public ResponseJSON delCommodity(HttpServletRequest request, @RequestBody Commodity commodity) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("下架失败");
        if (!checkUser(request)) {
            responseJSON.setMsg("用户权限不足，请联系管理员");
            return responseJSON;
        }
        CommodityBean commodityBean = commodityService.queryById(commodity.getCommodityId());
        if (commodityBean == null) {
            responseJSON.setMsg("暂无该商品");
            return responseJSON;
        }
        int i = commodityService.updateCommodityDel(commodity.getCommodityId());
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
