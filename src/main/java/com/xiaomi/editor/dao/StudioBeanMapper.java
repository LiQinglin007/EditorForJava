package com.xiaomi.editor.dao;

import com.xiaomi.editor.bean.StudioBean;

public interface StudioBeanMapper {
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