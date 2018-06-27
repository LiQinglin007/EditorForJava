package com.xiaomi.editor.utils;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-27<br>
 * Time: 13:56<br>
 * UpdateDescription：<br>
 */
public class JedisUtil {
    /**
     * 获取系统用户id
     *
     * @param token
     * @return
     */
    public static String getSystemUserId(String token) {
        return JedisClientUtil.getString(FinalData.SYSTEM_TOKEN + token);
    }

    /**
     * 获取app用户id
     *
     * @param token
     * @return
     */
    public static String getAppUserId(String token) {
        return JedisClientUtil.getString(FinalData.APP_TOKEN + token);
    }
}
