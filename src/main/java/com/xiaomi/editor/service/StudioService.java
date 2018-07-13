package com.xiaomi.editor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.xiaomi.editor.bean.StudioBean;
import com.xiaomi.editor.dao.StudioBeanMapper;
import com.xiaomi.editor.paramsbean.PageListBeanSearch;
import com.xiaomi.editor.utils.PinyinUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 8:51<br>
 * UpdateDescription：<br>
 */
@Service
public class StudioService implements IStudioService {

    @Resource
    StudioBeanMapper studioBeanMapper;


    /**
     * 增加工作室
     *
     * @param mStudioBean
     * @return 被影响的数量
     */
    @Override
    public int addStudio(StudioBean mStudioBean) {
        int i = studioBeanMapper.addStudio(mStudioBean);
        return i > 0 ? mStudioBean.getStudioId() : 0;
    }

    /**
     * 通过工作室id删除工作室(修改状态)
     *
     * @param mStudioBeanId 工作室id
     * @return 被影响的数量
     */
    @Override
    public int updateDelState(int mStudioBeanId) {
        return studioBeanMapper.updateDelState(mStudioBeanId);
    }

    /**
     * 通过id  查询没有被关的工作室
     *
     * @param mStudioBeanId
     * @return
     */
    @Override
    public StudioBean queryById(int mStudioBeanId) {
        return studioBeanMapper.queryById(mStudioBeanId);
    }

    @Override
    public Map<String, Object> queryStudioDeById(int mStudioBeanId) {
        return studioBeanMapper.queryStudioDeById(mStudioBeanId);
    }

    /**
     * 工作室修改自己工作室的数据
     *
     * @param mStudioBean
     * @return
     */
    @Override
    public int updateStudio(StudioBean mStudioBean) {
        return studioBeanMapper.updateStudio(mStudioBean);
    }

    @Override
    public int updateStudioByAdmin(StudioBean mStudioBean) {
        return studioBeanMapper.updateStudioByAdmin(mStudioBean);
    }

    @Override
    public StudioBean querySystenUserIdByCommodityId(int commodityId) {
        return null;
    }

    @Override
    public PageInfo selectByPageList(PageListBeanSearch pageBean) throws PinyinException {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        String searchContentPin = PinyinUtil.getPinyin(pageBean.getSearchContent());
        List studioBeanList = studioBeanMapper.selectStudioByPageList(searchContentPin);
        PageInfo pageInfo = new PageInfo<>(studioBeanList);
        return pageInfo;
    }

}
