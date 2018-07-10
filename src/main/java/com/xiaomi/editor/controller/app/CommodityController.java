package com.xiaomi.editor.controller.app;

import com.github.pagehelper.PageInfo;
import com.xiaomi.editor.paramsbean.PageListBean;
import com.xiaomi.editor.paramsbean.PageListBeanStudioId;
import com.xiaomi.editor.service.ICommodityService;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-09<br>
 * Time: 9:04<br>
 * UpdateDescription：<br>
 */
@RequestMapping("api/commodity")
@Controller
public class CommodityController {
    @Resource
    ICommodityService mICommodityService;


    /**
     * 获取工作室内的全部商品
     *
     * @param pageListBeanStudioId
     * @return
     */
    @RequestMapping("/getCommodityByStudioId")
    @ResponseBody
    public ResponseJSON getCommodityByStudioId(@RequestBody PageListBeanStudioId pageListBeanStudioId) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("获取失败");
        PageInfo pageInfo = mICommodityService.selectByPageList(pageListBeanStudioId);
        responseJSON = ResponseUtils.getSuccessResponseBean("获取成功", pageInfo);
        return responseJSON;
    }

    /**
     * 获取全部热门商品
     *
     * @param pageListBean
     * @return
     */
    @RequestMapping("/getHotCommodityList")
    @ResponseBody
    public ResponseJSON getHotCommodityList(@RequestBody PageListBean pageListBean) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("获取失败");
        PageInfo pageInfo = mICommodityService.selectHotCommodityByPageList(pageListBean);
        responseJSON = ResponseUtils.getSuccessResponseBean("获取成功", pageInfo);
        return responseJSON;
    }


}
