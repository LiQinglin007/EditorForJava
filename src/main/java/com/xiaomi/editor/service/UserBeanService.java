package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.UserBean;
import com.xiaomi.editor.dao.UserBeanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: 用户service<br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-21<br>
 * Time: 13:11<br>
 * UpdateDescription：<br>
 */
@Service
public class UserBeanService implements IUserBeanService {

    @Resource
    UserBeanMapper userBeanMapper;

    /**
     * 插入
     *
     * @param userBean
     * @return
     */
    @Override
    public int insert(UserBean userBean) {
        return userBeanMapper.insert(userBean);
    }

    @Override
    public List<UserBean> selectByPhone(String phone) {
        return userBeanMapper.selectByPhone(phone);
    }
}
