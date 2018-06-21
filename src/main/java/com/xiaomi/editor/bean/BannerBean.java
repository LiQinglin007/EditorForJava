package com.xiaomi.editor.bean;

/**
 * 轮播图
 */
public class BannerBean {
    /**
     * 轮播图id
     */
    private Integer bannerId;

    /**
     * 图片地址
     */
    private String bannerUrl;

    /**
     * 权重  觉得轮播图的排列顺序
     */
    private Short bannerWeight;

    /**
     * 链接地址
     */
    private String bannerWebUrl;

    public BannerBean(Integer bannerId, String bannerUrl, Short bannerWeight, String bannerWebUrl) {
        this.bannerId = bannerId;
        this.bannerUrl = bannerUrl;
        this.bannerWeight = bannerWeight;
        this.bannerWebUrl = bannerWebUrl;
    }

    public BannerBean() {
        super();
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    public Short getBannerWeight() {
        return bannerWeight;
    }

    public void setBannerWeight(Short bannerWeight) {
        this.bannerWeight = bannerWeight;
    }

    public String getBannerWebUrl() {
        return bannerWebUrl;
    }

    public void setBannerWebUrl(String bannerWebUrl) {
        this.bannerWebUrl = bannerWebUrl == null ? null : bannerWebUrl.trim();
    }
}