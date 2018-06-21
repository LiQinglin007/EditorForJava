package com.xiaomi.editor.bean;

/**
 * 服务
 */
public class ServletBean {
    /**
     * 服务id
     */
    private Integer servletId;

    /**
     * 工作室id
     */
    private Integer studioId;

    /**
     * 服务名称
     */
    private String servletName;

    /**
     * 服务图片
     */
    private String servletPic;

    /**
     * 详细图片
     */
    private String servletPics;

    /**
     * 原价
     */
    private Float servletOriginalPrice;

    /**
     * 现价
     */
    private Float servletPresentPrice;

    /**
     * 服务类型(1:查重、2:降重、3:速审)
     */
    private Short servletType;

    /**
     * 月销量
     */
    private Integer servletMonthlySales;

    /**
     * 收藏数量
     */
    private Integer servletCollectionQuantity;

    /**
     * 是否为热销服务
     */
    private Short servletHot;

    public ServletBean(Integer servletId, Integer studioId, String servletName, String servletPic, String servletPics, Float servletOriginalPrice, Float servletPresentPrice, Short servletType, Integer servletMonthlySales, Integer servletCollectionQuantity, Short servletHot) {
        this.servletId = servletId;
        this.studioId = studioId;
        this.servletName = servletName;
        this.servletPic = servletPic;
        this.servletPics = servletPics;
        this.servletOriginalPrice = servletOriginalPrice;
        this.servletPresentPrice = servletPresentPrice;
        this.servletType = servletType;
        this.servletMonthlySales = servletMonthlySales;
        this.servletCollectionQuantity = servletCollectionQuantity;
        this.servletHot = servletHot;
    }

    public ServletBean() {
        super();
    }

    public Integer getServletId() {
        return servletId;
    }

    public void setServletId(Integer servletId) {
        this.servletId = servletId;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName == null ? null : servletName.trim();
    }

    public String getServletPic() {
        return servletPic;
    }

    public void setServletPic(String servletPic) {
        this.servletPic = servletPic == null ? null : servletPic.trim();
    }

    public String getServletPics() {
        return servletPics;
    }

    public void setServletPics(String servletPics) {
        this.servletPics = servletPics == null ? null : servletPics.trim();
    }

    public Float getServletOriginalPrice() {
        return servletOriginalPrice;
    }

    public void setServletOriginalPrice(Float servletOriginalPrice) {
        this.servletOriginalPrice = servletOriginalPrice;
    }

    public Float getServletPresentPrice() {
        return servletPresentPrice;
    }

    public void setServletPresentPrice(Float servletPresentPrice) {
        this.servletPresentPrice = servletPresentPrice;
    }

    public Short getServletType() {
        return servletType;
    }

    public void setServletType(Short servletType) {
        this.servletType = servletType;
    }

    public Integer getServletMonthlySales() {
        return servletMonthlySales;
    }

    public void setServletMonthlySales(Integer servletMonthlySales) {
        this.servletMonthlySales = servletMonthlySales;
    }

    public Integer getServletCollectionQuantity() {
        return servletCollectionQuantity;
    }

    public void setServletCollectionQuantity(Integer servletCollectionQuantity) {
        this.servletCollectionQuantity = servletCollectionQuantity;
    }

    public Short getServletHot() {
        return servletHot;
    }

    public void setServletHot(Short servletHot) {
        this.servletHot = servletHot;
    }
}