package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.CommodityBean;
import com.xiaomi.editor.dao.CommodityBeanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 9:03<br>
 * UpdateDescription：<br>
 */
@Service
public class CommodityService implements ICommodityService {

    @Resource
    CommodityBeanMapper commodityBeanMapper;

    /**
     * 修改商品的热门状态
     *
     * @param CommodityMap
     * @return
     */
    @Override
    public int updateHotState(Map<String, Object> CommodityMap) {
        return commodityBeanMapper.updateHotState(CommodityMap);
    }

    @Override
    public CommodityBean queryById(int CommodityId) {
        return commodityBeanMapper.queryById(CommodityId);
    }
}
