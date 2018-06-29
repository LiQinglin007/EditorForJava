package com.xiaomi.editor.service;

import com.github.pagehelper.PageInfo;
import com.xiaomi.editor.bean.BannerBean;
import com.xiaomi.editor.bean.StudioBean;
import com.xiaomi.editor.paramsbean.PageListBean;

import java.util.List;

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


    /**
     * 工作室修改自己工作室的数据
     *
     * @param mStudioBean
     * @return
     */
    int updateStudio(StudioBean mStudioBean);
    /**
     * 超级管理员修改工作室数据
     *
     * @param mStudioBean
     * @return
     */
    int updateStudioByAdmin(StudioBean mStudioBean);

    /**
     * 通过商品的id来查询这个商品是所属店铺的信息
     *
     * @param commodityId
     * @return
     */
    StudioBean querySystenUserIdByCommodityId(int commodityId);

    /**
     * 分页查询
     *
     * @return
     */
    PageInfo selectByPage(PageListBean pageBean);

    /**
     * 按工作室名称模糊查询
     *
     * @param studioBeanNamePin
     * @return
     */
    List selectByStudioBeanNamePin(String studioBeanNamePin);
}
