package com.xiaomi.editor.controller.app;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.xiaomi.editor.paramsbean.PageListBeanSearch;
import com.xiaomi.editor.service.IStudioService;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.ResponseUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-09<br>
 * Time: 9:31<br>
 * UpdateDescription：<br>
 */
@Controller
@RequestMapping("api/studio")
public class StudioController {

    @Resource
    IStudioService mIStudioService;

    Logger logger = Logger.getLogger(StudioController.class);

    @RequestMapping("getStudioList")
    @ResponseBody
    public ResponseJSON getStudioList(@RequestBody PageListBeanSearch pageListBeanSearch) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("获取失败");
        try {
            mIStudioService.selectByPageList(pageListBeanSearch);
        } catch (PinyinException e) {
            logger.error("api/studio/getStudioList:");
            e.printStackTrace();
        }

        return responseJSON;
    }


}
