package com.xiaomi.editor.paramsbean;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-29<br>
 * Time: 13:05<br>
 * UpdateDescription：<br>
 */
public class SystemUser {
    private int UserId;
    private String UserLoginName;
    private String UserPassword;
    private int SystemUserType;

    public SystemUser(int userId, String userLoginName, String userPassword, int systemUserType) {
        UserId = userId;
        UserLoginName = userLoginName;
        UserPassword = userPassword;
        SystemUserType = systemUserType;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserLoginName() {
        return UserLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        UserLoginName = userLoginName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public int getSystemUserType() {
        return SystemUserType;
    }

    public void setSystemUserType(int systemUserType) {
        SystemUserType = systemUserType;
    }
}
