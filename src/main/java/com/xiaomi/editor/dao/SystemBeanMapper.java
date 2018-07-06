package com.xiaomi.editor.dao;


import com.xiaomi.editor.bean.SystemBean;

import java.util.List;

public interface SystemBeanMapper {
    /**
     * 添加系统用户
     *
     * @param mSystemBean
     * @return
     */
    int addSystemUserReturnId(SystemBean mSystemBean);

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
     * 修改密码
     *
     * @param mSystemBean
     * @return
     */
    int updatePassword(SystemBean mSystemBean);

    /**
     * 分页查询
     *
     * @return
     */
    List selectByPageList(String searchContent);
}