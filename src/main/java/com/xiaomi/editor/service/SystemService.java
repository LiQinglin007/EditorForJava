package com.xiaomi.editor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.xiaomi.editor.bean.SystemBean;
import com.xiaomi.editor.dao.SystemBeanMapper;
import com.xiaomi.editor.paramsbean.PageListBean;
import com.xiaomi.editor.paramsbean.PageListBeanSearch;
import com.xiaomi.editor.utils.PinyinUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 10:49<br>
 * UpdateDescription：<br>
 */
//这里开启事务
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class SystemService implements ISystemService {

    @Resource
    SystemBeanMapper systemBeanMapper;


    @Override
    public int addSystemUserReturnId(SystemBean mSystemBean) {
        int i = systemBeanMapper.addSystemUserReturnId(mSystemBean);
        //这里去制造一个异常  然后回滚
//        int a = 1 / 0;
        return i > 0 ? mSystemBean.getSystemUserid() : 0;
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
    public PageInfo selectByPageList(PageListBeanSearch pageListBean) throws PinyinException {
        PageHelper.startPage(pageListBean.getPage(), pageListBean.getSize());
        String pinyin = PinyinUtil.getPinyin(pageListBean.getSearchContent());
        List systemBeanList = systemBeanMapper.selectByPageList(pinyin);
        PageInfo pageInfo = new PageInfo<>(systemBeanList);
        return pageInfo;
    }

    @Override
    public List selectNoHaveStudio() {
        return systemBeanMapper.selectNoHaveStudio();
    }


}
