package com.xiaomi.editor.controller.app;

import com.github.pagehelper.PageInfo;
import com.xiaomi.editor.paramsbean.PageListBean;
import com.xiaomi.editor.service.INoticeBeanService;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.PageUtil;
import com.xiaomi.editor.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-18<br>
 * Time: 9:23<br>
 * UpdateDescription：<br>
 */
@Controller
@RequestMapping("api/notice")
public class NoticeController {

    @Resource
    INoticeBeanService noticeBeanService;


    @ResponseBody
    @RequestMapping(value = "getNoticeList", method = RequestMethod.POST)
    public ResponseJSON getNoticeList(@RequestBody PageListBean pageListBean) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("获取失败");
        PageInfo pageInfo = noticeBeanService.selectByPageList(pageListBean);
        HashMap<String, Object> pageInfoForApp = PageUtil.getPageInfoForApp(pageInfo);
        responseJSON = ResponseUtils.getSuccessResponseBean("获取成功", pageInfoForApp);
        return responseJSON;
    }

}
