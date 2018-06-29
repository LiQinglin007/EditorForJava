package com.xiaomi.editor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaomi.editor.bean.CommodityBean;
import com.xiaomi.editor.dao.CommodityBeanMapper;
import com.xiaomi.editor.paramsbean.PageListBean;
import com.xiaomi.editor.paramsbean.PageListBeanStudioId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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

    @Override
    public int addCommodity(CommodityBean commodityBean) {
        return commodityBeanMapper.addCommodity(commodityBean);
    }

    @Override
    public int updateCommodity(CommodityBean commodityBean) {
        return commodityBeanMapper.updateCommodity(commodityBean);
    }

    @Override
    public int updateCommodityDel(int CommodityId) {
        return commodityBeanMapper.updateCommodityDel(CommodityId);
    }

    @Override
    public PageInfo selectByPage(PageListBeanStudioId pageListBeanStudioId) {
        PageHelper.startPage(pageListBeanStudioId.getPage(), pageListBeanStudioId.getSize());
        List commodityBeanList = commodityBeanMapper.selectByStudioId(pageListBeanStudioId.getStudioId());
        PageInfo pageInfo = new PageInfo<>(commodityBeanList);
        return pageInfo;
    }

    @Override
    public PageInfo selectNotHotCommodityByPage(PageListBeanStudioId pageListBeanStudioId) {
        PageHelper.startPage(pageListBeanStudioId.getPage(), pageListBeanStudioId.getSize());
        List commodityBeanList = commodityBeanMapper.selectNotHotCommodityByPage(pageListBeanStudioId.getStudioId());
        PageInfo pageInfo = new PageInfo<>(commodityBeanList);
        return pageInfo;
    }

    @Override
    public PageInfo selectHotCommodityByPage(PageListBean pageListBean) {
        PageHelper.startPage(pageListBean.getPage(), pageListBean.getSize());
        List commodityBeanList = commodityBeanMapper.selectHotCommodityByPage();
        PageInfo pageInfo = new PageInfo<>(commodityBeanList);
        return pageInfo;
    }

    @Override
    public List<CommodityBean> selectByCommodityNamePin(String commodityNamePin) {
        return commodityBeanMapper.selectByCommodityNamePin(commodityNamePin);
    }


}
