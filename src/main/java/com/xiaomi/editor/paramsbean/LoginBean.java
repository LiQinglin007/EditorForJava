package com.xiaomi.editor.paramsbean;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-05<br>
 * Time: 10:04<br>
 * UpdateDescription：<br>
 */
public class LoginBean {
    private String phoneNumber;
    private String password;

    public LoginBean(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
