package com.xiaomi.editor.utils;

import java.util.List;

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
     * 保存系统token
     *
     * @param token
     * @param userId
     */
    public static void saveSystemToken(String token, int userId) {
        JedisClientUtil.saveString(FinalData.SYSTEM_TOKEN + token, userId + "", FinalData.TOKEN_EXPIRY_SECONDS);
    }


    /**
     * 获取所有系统用户token
     *
     * @return
     */
    public static List<String> getAllSystemToken() {
        return JedisClientUtil.getAllKeys(FinalData.SYSTEM_TOKEN + "*");
    }


    /**
     * 通过key来更新有效时间
     *
     * @param key
     */
    public static void setTokenTime(String key) {
        JedisClientUtil.setExpiryTime(key, FinalData.TOKEN_EXPIRY_SECONDS);
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


    /**
     * 获取这个手机号
     *
     * @return
     */
    public static String getRegisterCode(String phone) {
        return JedisClientUtil.getString(FinalData.REGISTER_CODE + phone);
    }


    /**
     * 保存注册的短信验证码
     *
     * @param phone 手机号
     * @param code  验证码
     * @param time  多久之内不能重复获取
     */
    public static void saveRegisterCode(String phone, String code, long time) {
        JedisClientUtil.saveString(FinalData.REGISTER_CODE + phone, code + "," + time, FinalData.MESSAGE_CODE_EXPIRY_SECONDS);
    }


    /**
     * 修改验证码和时间
     *
     * @param key
     * @param code
     * @param time
     */
    public static void updateRegisterCode(String key, String code, long time) {
        JedisClientUtil.saveString(key, code + "," + time, FinalData.MESSAGE_CODE_EXPIRY_SECONDS);
    }


}
