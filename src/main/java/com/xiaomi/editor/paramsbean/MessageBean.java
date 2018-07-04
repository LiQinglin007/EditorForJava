package com.xiaomi.editor.paramsbean;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-04<br>
 * Time: 16:11<br>
 * UpdateDescription：<br>
 */
public class MessageBean {
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 类型   1：注册  2：找回密码
     */
    private int messageType;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
