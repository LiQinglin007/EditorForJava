package com.xiaomi.editor.paramsbean;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-05<br>
 * Time: 17:54<br>
 * UpdateDescription：<br>
 */
public class ForgotPasswordBean {
    private String phoneNumber;
    private String messageCode;
    private String password;

    public ForgotPasswordBean(String phoneNumber, String messageCode, String password) {
        this.phoneNumber = phoneNumber;
        this.messageCode = messageCode;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
