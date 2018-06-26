package com.xiaomi.editor.bean;

/**
 * 服务(商品)
 */
public class CommodityBean {
    /**
     * 服务id
     */
    private Integer commodityId;
    /**
     * 工作室id
     */
    private Integer studioId;
    /**
     * 服务名称
     */
    private String commodityName;
    /**
     * 服务图片
     */
    private String commodityPic;
    /**
     * 详细图片
     */
    private String commodityPics;
    /**
     * 原价
     */
    private Float commodityOriginalPrice;
    /**
     * 现价
     */
    private Float commodityPresentPrice;
    /**
     * 服务类型(1:查重、2:降重、3:速审)
     */
    private Short commodityType;
    /**
     * 月销量
     */
    private Integer commodityMonthlySales;
    /**
     * 收藏数量
     */
    private Integer commodityCollectionQuantity;
    /**
     * 是否为热销服务(0:不是  1：热门)
     */
    private Short commodityHot;
    /**
     * 是否被删除  0：不被删除  1：被删除了
     */
    private Short commodityDel;

    public CommodityBean(Integer commodityId, Integer studioId, String commodityName, String commodityPic, String commodityPics, Float commodityOriginalPrice, Float commodityPresentPrice, Short commodityType, Integer commodityMonthlySales, Integer commodityCollectionQuantity, Short commodityHot, Short commodityDel) {
        this.commodityId = commodityId;
        this.studioId = studioId;
        this.commodityName = commodityName;
        this.commodityPic = commodityPic;
        this.commodityPics = commodityPics;
        this.commodityOriginalPrice = commodityOriginalPrice;
        this.commodityPresentPrice = commodityPresentPrice;
        this.commodityType = commodityType;
        this.commodityMonthlySales = commodityMonthlySales;
        this.commodityCollectionQuantity = commodityCollectionQuantity;
        this.commodityHot = commodityHot;
        this.commodityDel = commodityDel;
    }


    public CommodityBean(Integer studioId, String commodityName, String commodityPic, String commodityPics,  Float commodityPresentPrice, Short commodityType) {
        this.studioId = studioId;
        this.commodityName = commodityName;
        this.commodityPic = commodityPic;
        this.commodityPics = commodityPics;
        this.commodityOriginalPrice = commodityPresentPrice;
        this.commodityPresentPrice = commodityPresentPrice;
        this.commodityType = commodityType;
        this.commodityMonthlySales = 0;
        this.commodityCollectionQuantity = 0;
        this.commodityHot = 0;
        this.commodityDel = 0;
    }




    public CommodityBean() {
        super();
    }


    public Short getCommodityDel() {
        return commodityDel;
    }

    public void setCommodityDel(Short commodityDel) {
        this.commodityDel = commodityDel;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getCommodityPic() {
        return commodityPic;
    }

    public void setCommodityPic(String commodityPic) {
        this.commodityPic = commodityPic == null ? null : commodityPic.trim();
    }

    public String getCommodityPics() {
        return commodityPics;
    }

    public void setCommodityPics(String commodityPics) {
        this.commodityPics = commodityPics == null ? null : commodityPics.trim();
    }

    public Float getCommodityOriginalPrice() {
        return commodityOriginalPrice;
    }

    public void setCommodityOriginalPrice(Float commodityOriginalPrice) {
        this.commodityOriginalPrice = commodityOriginalPrice;
    }

    public Float getCommodityPresentPrice() {
        return commodityPresentPrice;
    }

    public void setCommodityPresentPrice(Float commodityPresentPrice) {
        this.commodityPresentPrice = commodityPresentPrice;
    }

    public Short getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(Short commodityType) {
        this.commodityType = commodityType;
    }

    public Integer getCommodityMonthlySales() {
        return commodityMonthlySales;
    }

    public void setCommodityMonthlySales(Integer commodityMonthlySales) {
        this.commodityMonthlySales = commodityMonthlySales;
    }

    public Integer getCommodityCollectionQuantity() {
        return commodityCollectionQuantity;
    }

    public void setCommodityCollectionQuantity(Integer commodityCollectionQuantity) {
        this.commodityCollectionQuantity = commodityCollectionQuantity;
    }

    public Short getCommodityHot() {
        return commodityHot;
    }

    public void setCommodityHot(Short commodityHot) {
        this.commodityHot = commodityHot;
    }
}