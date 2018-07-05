package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.UserBean;
import com.xiaomi.editor.paramsbean.ForgotPasswordBean;

import java.util.List;
import java.util.Map;

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
