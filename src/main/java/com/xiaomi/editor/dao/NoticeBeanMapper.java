package com.xiaomi.editor.dao;

import com.xiaomi.editor.bean.NoticeBean;

import java.util.List;

public interface NoticeBeanMapper {
    /**
     * 添加公告
     *
     * @param mNoticeBean
     * @return
     */
    int addNotice(NoticeBean mNoticeBean);


    /**
     * 删除公告(修改状态)
     *
     * @param noticeId
     * @return
     */
    int updateDelState(int noticeId);


    /**
     * 查询没有被删除的公告
     *
     * @param noticeId
     * @return
     */
    NoticeBean queryById(int noticeId);

    /**
     * 分页查询
     *
     * @return
     */
    List<NoticeBean> selectByPageList();


}