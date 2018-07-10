package com.xiaomi.editor.controller.app;

import com.github.pagehelper.PageInfo;
import com.xiaomi.editor.paramsbean.PageListBean;
import com.xiaomi.editor.service.IBannerService;
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
 * Time: 8:56<br>
 * UpdateDescription：<br>
 */
@RequestMapping("api/banner")
@Controller
public class BannerController {

    @Resource
    IBannerService mIBannerService;

    /**
     * 获取banner列表
     *
     * @param pageListBean
     * @return
     */
    @ResponseBody
    @RequestMapping("getBannerList")
    public ResponseJSON getBannerList(@RequestBody PageListBean pageListBean) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("获取失败");
        PageInfo pageInfo = mIBannerService.selectByPageList(pageListBean);
        responseJSON = ResponseUtils.getSuccessResponseBean("获取成功", pageInfo);
        return responseJSON;
    }






}
