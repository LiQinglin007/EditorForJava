package com.xiaomi.editor.dao;

import com.xiaomi.editor.bean.UserBean;

import java.util.List;

public interface UserBeanMapper {

    /**
     * 插入
     *
     * @param userBean
     * @return
     */
    int insert(UserBean userBean);

    /**
     * 查询这个手机号有没有被注册
     *
     * @param phone
     */
    List<UserBean> selectByPhone(String phone);

}