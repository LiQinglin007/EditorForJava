package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.CommodityBean;

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

}
