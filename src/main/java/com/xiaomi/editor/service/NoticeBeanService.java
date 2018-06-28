package com.xiaomi.editor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaomi.editor.bean.NoticeBean;
import com.xiaomi.editor.dao.NoticeBeanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.print.Doc;
import java.util.List;
import java.util.Map;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 11:34<br>
 * UpdateDescription：<br>
 */


@Service
public class NoticeBeanService implements INoticeBeanService {
    @Resource
    NoticeBeanMapper noticeBeanMapper;


    @Override
    public int addNotice(NoticeBean mNoticeBean) {
        return noticeBeanMapper.addNotice(mNoticeBean);
    }

    @Override
    public int updateDelState(int noticeId) {
        return noticeBeanMapper.updateDelState(noticeId);
    }

    @Override
    public NoticeBean queryById(int noticeId) {
        return noticeBeanMapper.queryById(noticeId);
    }

    @Override
    public PageInfo selectByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List noticeBeanList = noticeBeanMapper.selectByPage();
        PageInfo pageInfo = new PageInfo<>(noticeBeanList);
        return pageInfo;
    }
}
