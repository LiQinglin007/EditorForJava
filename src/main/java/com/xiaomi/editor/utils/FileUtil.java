package com.xiaomi.editor.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-26<br>
 * Time: 8:45<br>
 * UpdateDescription：<br>
 */
public class FileUtil {

    /**
     * 保存文件
     *
     * @param session
     * @param file
     * @return
     * @throws IOException
     */
    public static String saveFile(HttpSession session, MultipartFile file) throws IOException {
        String RootPath = session.getServletContext().getRealPath("/");
        String savePath = "static/" + System.currentTimeMillis() + ".jpg";
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(RootPath + savePath));
        return savePath;
    }
}
