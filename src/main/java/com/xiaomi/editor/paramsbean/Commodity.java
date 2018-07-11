package com.xiaomi.editor.paramsbean;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-29<br>
 * Time: 14:37<br>
 * UpdateDescription：<br>
 */
public class Commodity {
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
     * 服务名称(拼音)
     */
    private String commodityNamePin;
    /**
     * 商品介绍
     */
    private String commodityIntroduce;
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
        this.commodityName = commodityName;
    }

    public String getCommodityNamePin() {
        return commodityNamePin;
    }

    public void setCommodityNamePin(String commodityNamePin) {
        this.commodityNamePin = commodityNamePin;
    }

    public String getCommodityIntroduce() {
        return commodityIntroduce;
    }

    public void setCommodityIntroduce(String commodityIntroduce) {
        this.commodityIntroduce = commodityIntroduce;
    }

    public String getCommodityPic() {
        return commodityPic;
    }

    public void setCommodityPic(String commodityPic) {
        this.commodityPic = commodityPic;
    }

    public String getCommodityPics() {
        return commodityPics;
    }

    public void setCommodityPics(String commodityPics) {
        this.commodityPics = commodityPics;
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

    public Short getCommodityDel() {
        return commodityDel;
    }

    public void setCommodityDel(Short commodityDel) {
        this.commodityDel = commodityDel;
    }

}
