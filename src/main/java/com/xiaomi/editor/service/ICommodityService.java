package com.xiaomi.editor.service;

import com.github.pagehelper.PageInfo;
import com.xiaomi.editor.bean.BannerBean;
import com.xiaomi.editor.bean.CommodityBean;
import com.xiaomi.editor.paramsbean.PageListBean;
import com.xiaomi.editor.paramsbean.PageListBeanStudioId;

import java.util.List;
import java.util.Map;

/**
 * Description: 服务(商品)<br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 9:01<br>
 * UpdateDescription：<br>
 */
public interface ICommodityService {

    /**
     * 修改商品的热门状态
     *
     * @return
     */
    int updateHotState(Map<String, Object> CommodityMap);

    /**
     * 查询没有被删除的商品
     *
     * @param CommodityId
     * @return
     */
    CommodityBean queryById(int CommodityId);

    /**
     * 添加商品
     *
     * @param commodityBean
     * @return
     */
    int addCommodity(CommodityBean commodityBean);

    /**
     * 工作室修改自家的商品
     *
     * @param commodityBean
     * @return
     */
    int updateCommodity(CommodityBean commodityBean);

    /**
     * 下架商品
     *
     * @param CommodityId
     * @return
     */
    int updateCommodityDel(int CommodityId);


    /**
     * 查询工作室中所有商品
     *
     * @param studioId    工作室id
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo selectByPage( PageListBeanStudioId pageListBeanStudioId);

    /**
     * 查询某个工作室中非热门商品
     *
     * @param studioId    工作室id
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo selectNotHotCommodityByPage( PageListBeanStudioId pageListBeanStudioId);

    /**
     * 分页查询全部热门商品
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo selectHotCommodityByPage(PageListBean pageListBean);


    /**
     * 按商品名称模糊查询
     *
     * @param commodityNamePin
     * @return
     */
    List<CommodityBean> selectByCommodityNamePin(String commodityNamePin);


}
