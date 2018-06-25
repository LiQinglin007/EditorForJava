package com.xiaomi.editor.dao;

import com.xiaomi.editor.bean.BannerBean;

import java.util.List;

public interface BannerBeanMapper {
    int addBanner(BannerBean mBannerBean);

    int updateDelState(int mBannerId);

    int updateBanner(BannerBean mBannerBean);

    List<BannerBean> selectAll();
}