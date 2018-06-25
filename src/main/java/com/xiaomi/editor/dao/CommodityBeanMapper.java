package com.xiaomi.editor.dao;

import com.xiaomi.editor.bean.CommodityBean;

import java.util.Map;

public interface CommodityBeanMapper {
    /**
     * 修改商品的热门状态
     *
     * @return
     */
    int updateHotState(Map<String,Object> CommodityMap);

    /**
     * 查询没有被删除的商品
     *
     * @param CommodityId
     * @return
     */
    CommodityBean queryById(int CommodityId);


}