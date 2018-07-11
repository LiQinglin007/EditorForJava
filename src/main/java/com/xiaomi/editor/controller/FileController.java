package com.xiaomi.editor.controller;

import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.FileUtil;
import com.xiaomi.editor.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-11<br>
 * Time: 9:25<br>
 * UpdateDescription：<br>
 */
@Controller
@RequestMapping("upload")
public class FileController {

    /**
     * 文件的上传
     *
     * @param session
     * @return
     * @throws IllegalStateException
     */
    @RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON uploadFiles(HttpSession session, @RequestParam(value = "files") MultipartFile[] files) throws IOException {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("添加失败", null);
        String savePath = FileUtil.saveFile(session, files);
        responseJSON = ResponseUtils.getSuccessResponseBean("保存成功", savePath);
        return responseJSON;
    }
}
