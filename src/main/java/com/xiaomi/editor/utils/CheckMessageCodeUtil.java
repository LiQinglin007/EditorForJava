package com.xiaomi.editor.utils;


/**
 * Description:检查短信验证码 <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-21<br>
 * Time: 13:27<br>
 * UpdateDescription：<br>
 */
public class CheckMessageCodeUtil {
    public final static int MESSAGE_REGISTER = 0x51;//注册
    public final static int MESSAGE_LOGIN = 0x52;//登录
    public final static int MESSAGE_FINDPWD = 0x51;//找回密码
    public final static int MESSAGE_UPDATEPWD = 0x52;//修改密码
    public final static int MESSAGE_UPDATEPHONE = 0x53;//修改手机号

    /**
     * 检查短信验证码
     *
     * @param type  验证类型
     * @param phone 手机号码
     * @param code  短信验证码
     * @return 通过返回true 不通过 false
     */
    public static boolean checkCode(int type, String phone, String code) {
        if (code.length() < 6) {
            return false;
        }
        if (type == MESSAGE_REGISTER) {
            //根据手机号去获取验证码,看看存在不存在
            String registerCode = JedisUtil.getRegisterCode(phone);
            if (!CheckStringEmptyUtils.IsEmpty(registerCode)) {//存在,去看时间
                return true;
            }
        }
        return false;
    }
}
