package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.SystemBean;

/**
 * Description: 系统用户<br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 10:43<br>
 * UpdateDescription：<br>
 */
public interface ISystemService {

    /**
     * 添加系统用户
     *
     * @param mSystemBean
     * @return
     */
    int addUser(SystemBean mSystemBean);

    /**
     * 修改是否停用状态
     *
     * @param systemId
     * @return
     */
    int updateDelState(int systemId);

    /**
     * 通过id 查询 没有被删除的
     *
     * @param systemId
     * @return
     */
    SystemBean queryById(int systemId);


    /**
     * 通过用户名查询用户
     *
     * @param loginName
     * @return
     */
    SystemBean queryByLoginName(String loginName);

    /**
     * 通过用户id 修改密码
     *
     * @param mSystemBean
     * @return
     */
    int updatePassword(SystemBean mSystemBean);
}
