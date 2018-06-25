package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.BannerBean;

import java.util.List;

/**
 * Description: 首页轮播图<br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 8:52<br>
 * UpdateDescription：<br>
 */
public interface IBannerService {

    int addBanner(BannerBean mBannerBean);

    int updateDelState(int mBannerId);

    int updateBanner(BannerBean mBannerBean);

    List<BannerBean> selectAll();

}
