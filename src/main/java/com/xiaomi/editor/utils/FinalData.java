package com.xiaomi.editor.utils;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-26<br>
 * Time: 16:46<br>
 * UpdateDescription：<br>
 */
public class FinalData {
    private static final String PACKAGENAME = "COM.XIAOMI.EDITOR";
    /**
     * 用户手机号redis
     */
    public static final String CODE = PACKAGENAME + "USERMOBILE:";

    /**
     * 手机验证码 有效时长 （秒）
     */
    public static final int CODE_EXPIRY_SECONDS = 180;


    /**
     * app token
     */
    public static final String APP_TOKEN = PACKAGENAME + "USER_TOKEN:";


    /**
     * app token
     */
    public static final String SYSTEM_TOKEN = PACKAGENAME + "SYSTEM_USER_TOKEN:";


    /**
     * app token 失效时间  秒（12小时）
     */
    public static final int TOKEN_EXPIRY_SECONDS = 43200;
//    public static final int TOKEN_EXPIRY_SECONDS = 60;

    public static final String SYSTEM_BASEURL = "/system/";

    public static final String APP_BASEURL = "/api/";
    /**
     * 请求头中的token
     */
    public static final String TOKENHEAD = "Authorization";

    /**
     * 系统用户类型：超级管理员  和数据库保持一致
     */
    public static final int SYSTEM_ADMIN = 1;
    /**
     * 系统用户类型：工作室账号  和数据库保持一致
     */
    public static final int SYSTEM_STUDIO = 2;
}
