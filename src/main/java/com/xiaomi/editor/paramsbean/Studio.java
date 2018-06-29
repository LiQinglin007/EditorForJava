package com.xiaomi.editor.paramsbean;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-29<br>
 * Time: 14:30<br>
 * UpdateDescription：<br>
 */
public class Studio {
    /**
     * 工作室id
     */
    private Integer studioId;

    /**
     * 工作室名称
     */
    private String studioName;
    /**
     * 工作室名称(拼音)
     */
    private String studioNamePin;

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

    /**
     * 是否删除   0：不被删除  1：被删除了
     */
    private Short studioDel;

    /**
     * 系统用户id
     */
    private Integer systemUserid;


    private  MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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
        this.studioName = studioName;
    }

    public String getStudioNamePin() {
        return studioNamePin;
    }

    public void setStudioNamePin(String studioNamePin) {
        this.studioNamePin = studioNamePin;
    }

    public String getStudioPic() {
        return studioPic;
    }

    public void setStudioPic(String studioPic) {
        this.studioPic = studioPic;
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
        this.studioPhone = studioPhone;
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
        this.studioQq = studioQq;
    }

    public String getStudioBriefintroduction() {
        return studioBriefintroduction;
    }

    public void setStudioBriefintroduction(String studioBriefintroduction) {
        this.studioBriefintroduction = studioBriefintroduction;
    }

    public Integer getStudioCollectionNmuber() {
        return studioCollectionNmuber;
    }

    public void setStudioCollectionNmuber(Integer studioCollectionNmuber) {
        this.studioCollectionNmuber = studioCollectionNmuber;
    }

    public Short getStudioDel() {
        return studioDel;
    }

    public void setStudioDel(Short studioDel) {
        this.studioDel = studioDel;
    }

    public Integer getSystemUserid() {
        return systemUserid;
    }

    public void setSystemUserid(Integer systemUserid) {
        this.systemUserid = systemUserid;
    }
}
