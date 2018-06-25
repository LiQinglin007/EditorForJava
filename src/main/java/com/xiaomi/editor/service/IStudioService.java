package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.StudioBean;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 8:48<br>
 * UpdateDescription：<br>
 */
public interface IStudioService {
    /**
     * 增加工作室
     *
     * @param mStudioBean
     * @return 被影响的数量
     */
    int addStudio(StudioBean mStudioBean);


    /**
     * 通过工作室id删除工作室(修改状态)
     *
     * @param mStudioBeanId 工作室id
     * @return 被影响的数量
     */
    int updateDelState(int mStudioBeanId);


    /**
     * 通过id  查询没有被关的工作室
     *
     * @param mStudioBeanId
     * @return
     */
    StudioBean queryById(int mStudioBeanId);
}
