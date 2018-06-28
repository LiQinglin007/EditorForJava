package com.xiaomi.editor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaomi.editor.bean.BannerBean;
import com.xiaomi.editor.bean.SystemBean;
import com.xiaomi.editor.dao.SystemBeanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 10:49<br>
 * UpdateDescription：<br>
 */
@Service
public class SystemService implements ISystemService {

    @Resource
    SystemBeanMapper systemBeanMapper;

    @Override
    public int addUser(SystemBean mSystemBean) {
        return systemBeanMapper.addSystemUser(mSystemBean);
    }

    @Override
    public int updateDelState(int systemId) {
        return systemBeanMapper.updateDelState(systemId);
    }

    @Override
    public SystemBean queryById(int systemId) {
        return systemBeanMapper.queryById(systemId);
    }

    @Override
    public SystemBean queryByLoginName(String loginName) {
        return systemBeanMapper.queryByLoginName(loginName);
    }

    @Override
    public int updatePassword(SystemBean mSystemBean) {
        return systemBeanMapper.updatePassword(mSystemBean);
    }

    @Override
    public PageInfo selectByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List systemBeanList = systemBeanMapper.selectByPage();
        PageInfo pageInfo = new PageInfo<>(systemBeanList);
        return pageInfo;
    }
}
