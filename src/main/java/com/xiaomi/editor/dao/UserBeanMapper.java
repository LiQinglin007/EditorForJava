package com.xiaomi.editor.dao;

import com.xiaomi.editor.bean.UserBean;
import com.xiaomi.editor.paramsbean.ForgotPasswordBean;

import java.util.List;
import java.util.Map;

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
    UserBean selectByPhone(String phone);

    /**
     * 找回密码
     *
     * @param map phoneNumber,password
     * @return
     */
    int updatePassword(Map<String, String> map);
}