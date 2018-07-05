package com.xiaomi.editor.dao;

import com.xiaomi.editor.bean.CommodityBean;

import java.util.List;
import java.util.Map;

public interface CommodityBeanMapper {
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
     * 分页查询
     *
     * @return
     */
    List selectByStudioIdList(int studioId);

    /**
     * 查询某个店铺的全部非热门商品
     *
     * @return
     */
    List selectNotHotCommodityByPageList(int studioId);

    /**
     * 查询全部热门商品
     *
     * @return
     */
    List selectHotCommodityByPageList();


    /**
     * 按商品名称模糊查询
     *
     * @param commodityNamePin
     * @return
     */
    List<CommodityBean> selectByCommodityNamePinList(String commodityNamePin);

}