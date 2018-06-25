package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.StudioBean;
import com.xiaomi.editor.dao.StudioBeanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 8:51<br>
 * UpdateDescription：<br>
 */
@Service
public class StudioService implements IStudioService {

    @Resource
    StudioBeanMapper studioBeanMapper;

    @Override
    public int addStudio(StudioBean mStudioBean) {
        return 0;
    }

    @Override
    public int updateDelState(int mStudioBeanId) {
        return 0;
    }

    @Override
    public StudioBean queryById(int mStudioBeanId) {
        return null;
    }

}
