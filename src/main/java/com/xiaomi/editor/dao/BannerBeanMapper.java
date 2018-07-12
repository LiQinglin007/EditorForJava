package com.xiaomi.editor.dao;

import com.xiaomi.editor.bean.BannerBean;
import com.xiaomi.editor.bean.NoticeBean;

import java.util.List;

public interface BannerBeanMapper {
    int addBanner(BannerBean mBannerBean);
    int addBanner1(BannerBean mBannerBean);

    int updateDelState(int mBannerId);

    int updateBanner(BannerBean mBannerBean);

    List<BannerBean> selectAllList();

    /**
     * 分页查询
     *
     * @return
     */
    List<BannerBean> selectByPageList();

}