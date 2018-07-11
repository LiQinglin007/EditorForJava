package com.xiaomi.editor.paramsbean;

import org.springframework.web.multipart.MultipartFile;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-29<br>
 * Time: 14:25<br>
 * UpdateDescription：<br>
 */
public class Banner {
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

    /**
     * 是否删除   0：不被删除  1：被删除了
     */
    private Short bannerDel;

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
        this.bannerUrl = bannerUrl;
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
        this.bannerWebUrl = bannerWebUrl;
    }

    public Short getBannerDel() {
        return bannerDel;
    }

    public void setBannerDel(Short bannerDel) {
        this.bannerDel = bannerDel;
    }

    public Banner(Integer bannerId, String bannerUrl, Short bannerWeight, String bannerWebUrl, Short bannerDel) {
        this.bannerId = bannerId;
        this.bannerUrl = bannerUrl;
        this.bannerWeight = bannerWeight;
        this.bannerWebUrl = bannerWebUrl;
        this.bannerDel = bannerDel;
    }
}
