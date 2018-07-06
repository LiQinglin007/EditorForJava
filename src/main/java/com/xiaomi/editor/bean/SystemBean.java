package com.xiaomi.editor.bean;

public class SystemBean {
    private Integer systemUserid;

    private String systemUserLoginname;

    private String systemUserLoginnamePin;

    private String systemUserPassword;
    /**
     * 用户类型  1：超级管理员  2：工作室
     */
    private Short systemUserType;
    /**
     * 是否被删除  0：不被删除  1：被删除了
     */
    private Short systemUserDel;

    public String getSystemUserLoginnamePin() {
        return systemUserLoginnamePin;
    }

    public void setSystemUserLoginnamePin(String systemUserLoginnamePin) {
        this.systemUserLoginnamePin = systemUserLoginnamePin;
    }

    public SystemBean(Integer systemUserid, String systemUserLoginname, String systemUserLoginnamePin, String systemUserPassword, Short systemUserType, Short systemUserDel) {
        this.systemUserid = systemUserid;
        this.systemUserLoginname = systemUserLoginname;
        this.systemUserLoginnamePin = systemUserLoginnamePin;
        this.systemUserPassword = systemUserPassword;
        this.systemUserType = systemUserType;
        this.systemUserDel = systemUserDel;
    }



    public SystemBean(String systemUserLoginname, String systemUserLoginnamePin, String systemUserPassword) {
        this.systemUserid = null;
        this.systemUserLoginname = systemUserLoginname;
        this.systemUserLoginnamePin = systemUserLoginnamePin;
        this.systemUserPassword = systemUserPassword;
        this.systemUserType = 2;
        this.systemUserDel = 0;
    }

    public SystemBean() {
        super();
    }

    public Integer getSystemUserid() {
        return systemUserid;
    }

    public void setSystemUserid(Integer systemUserid) {
        this.systemUserid = systemUserid;
    }

    public String getSystemUserLoginname() {
        return systemUserLoginname;
    }

    public void setSystemUserLoginname(String systemUserLoginname) {
        this.systemUserLoginname = systemUserLoginname == null ? null : systemUserLoginname.trim();
    }

    public String getSystemUserPassword() {
        return systemUserPassword;
    }

    public void setSystemUserPassword(String systemUserPassword) {
        this.systemUserPassword = systemUserPassword == null ? null : systemUserPassword.trim();
    }

    public Short getSystemUserType() {
        return systemUserType;
    }

    public void setSystemUserType(Short systemUserType) {
        this.systemUserType = systemUserType;
    }

    public Short getSystemUserDel() {
        return systemUserDel;
    }

    public void setSystemUserDel(Short systemUserDel) {
        this.systemUserDel = systemUserDel;
    }
}