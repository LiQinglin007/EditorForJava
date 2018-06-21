package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.UserBean;

import java.util.List;

/**
 * Description: 用户模块<br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-21<br>
 * Time: 13:12<br>
 * UpdateDescription：<br>
 */
public interface IUserBeanService {
    /**
     * 插入
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
