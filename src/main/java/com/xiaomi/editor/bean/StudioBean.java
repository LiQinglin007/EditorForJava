package com.xiaomi.editor.bean;

/**
 * 工作室
 */
public class StudioBean {
    /**
     * 工作室id
     */
    private Integer studioId;

    /**
     * 工作室名称
     */
    private String studioName;

    /**
     * 工作室图片
     */
    private String studioPic;

    /**
     * 押金
     */
    private Float studioMoney;

    /**
     * 电话
     */
    private String studioPhone;

    /**
     * 月销量
     */
    private Integer studioMonthlySales;

    /**
     * 联系QQ
     */
    private String studioQq;

    /**
     * 简介
     */
    private String studioBriefintroduction;

    /**
     * 收藏数量
     */
    private Integer studioCollectionNmuber;

    public StudioBean(Integer studioId, String studioName, String studioPic, Float studioMoney, String studioPhone, Integer studioMonthlySales, String studioQq, String studioBriefintroduction, Integer studioCollectionNmuber) {
        this.studioId = studioId;
        this.studioName = studioName;
        this.studioPic = studioPic;
        this.studioMoney = studioMoney;
        this.studioPhone = studioPhone;
        this.studioMonthlySales = studioMonthlySales;
        this.studioQq = studioQq;
        this.studioBriefintroduction = studioBriefintroduction;
        this.studioCollectionNmuber = studioCollectionNmuber;
    }

    public StudioBean() {
        super();
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName == null ? null : studioName.trim();
    }

    public String getStudioPic() {
        return studioPic;
    }

    public void setStudioPic(String studioPic) {
        this.studioPic = studioPic == null ? null : studioPic.trim();
    }

    public Float getStudioMoney() {
        return studioMoney;
    }

    public void setStudioMoney(Float studioMoney) {
        this.studioMoney = studioMoney;
    }

    public String getStudioPhone() {
        return studioPhone;
    }

    public void setStudioPhone(String studioPhone) {
        this.studioPhone = studioPhone == null ? null : studioPhone.trim();
    }

    public Integer getStudioMonthlySales() {
        return studioMonthlySales;
    }

    public void setStudioMonthlySales(Integer studioMonthlySales) {
        this.studioMonthlySales = studioMonthlySales;
    }

    public String getStudioQq() {
        return studioQq;
    }

    public void setStudioQq(String studioQq) {
        this.studioQq = studioQq == null ? null : studioQq.trim();
    }

    public String getStudioBriefintroduction() {
        return studioBriefintroduction;
    }

    public void setStudioBriefintroduction(String studioBriefintroduction) {
        this.studioBriefintroduction = studioBriefintroduction == null ? null : studioBriefintroduction.trim();
    }

    public Integer getStudioCollectionNmuber() {
        return studioCollectionNmuber;
    }

    public void setStudioCollectionNmuber(Integer studioCollectionNmuber) {
        this.studioCollectionNmuber = studioCollectionNmuber;
    }
}