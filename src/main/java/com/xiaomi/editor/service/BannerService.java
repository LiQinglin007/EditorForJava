package com.xiaomi.editor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaomi.editor.bean.BannerBean;
import com.xiaomi.editor.dao.BannerBeanMapper;
import com.xiaomi.editor.paramsbean.PageListBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 8:54<br>
 * UpdateDescription：<br>
 */

@Service
public class BannerService implements IBannerService {

    @Resource
    BannerBeanMapper beanMapper;

    @Override
    public int addBanner(BannerBean mBannerBean) {
        int i = beanMapper.addBanner(mBannerBean);
        return i > 0 ?mBannerBean.getBannerId():0;
    }

    @Override
    public int updateDelState(int mBannerId) {
        return beanMapper.updateDelState(mBannerId);
    }

    @Override
    public int updateBanner(BannerBean mBannerBean) {
        return beanMapper.updateBanner(mBannerBean);
    }

    @Override
    public List<BannerBean> selectAllList() {
        return beanMapper.selectAllList();
    }

    @Override
    public PageInfo selectByPageList
            (PageListBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List bannerBeanList = beanMapper.selectByPageList();
        PageInfo pageInfo = new PageInfo<>(bannerBeanList);
        return pageInfo;
    }

}
