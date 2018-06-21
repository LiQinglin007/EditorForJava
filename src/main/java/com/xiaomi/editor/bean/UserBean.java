package com.xiaomi.editor.bean;

/**
 * 用户表
 */
public class UserBean {
    private Integer userId;
    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户头像
     */
    private String userPic;

    /**
     * 余额
     */
    private Float userBalance;

    /**
     * 电话(登录名)
     */
    private String userPhone;

    /**
     * 学校
     */
    private String userSchool;

    /**
     * 工作
     */
    private String userJob;

    /**
     * 学历
     */
    private String userEducation;

    public UserBean(Integer userId, String userName, String userPassword, String userPic, Float userBalance, String userPhone, String userSchool, String userJob, String userEducation) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPic = userPic;
        this.userBalance = userBalance;
        this.userPhone = userPhone;
        this.userSchool = userSchool;
        this.userJob = userJob;
        this.userEducation = userEducation;
    }

    /**
     * 用于注册
     *
     * @param userName
     * @param userPassword
     * @param userBalance
     * @param userPhone
     */
    public UserBean(String userName, String userPassword, Float userBalance, String userPhone) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPic = "";
        this.userBalance = userBalance;
        this.userPhone = userPhone;
        this.userSchool = "";
        this.userJob = "";
        this.userEducation = "";
    }

    public UserBean() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic == null ? null : userPic.trim();
    }

    public Float getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Float userBalance) {
        this.userBalance = userBalance;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool == null ? null : userSchool.trim();
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob == null ? null : userJob.trim();
    }

    public String getUserEducation() {
        return userEducation;
    }

    public void setUserEducation(String userEducation) {
        this.userEducation = userEducation == null ? null : userEducation.trim();
    }
}