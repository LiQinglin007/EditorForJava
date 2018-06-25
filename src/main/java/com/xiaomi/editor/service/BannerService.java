package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.BannerBean;
import com.xiaomi.editor.dao.BannerBeanMapper;
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
        return beanMapper.addBanner(mBannerBean);
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
    public List<BannerBean> selectAll() {
        return beanMapper.selectAll();
    }
}
