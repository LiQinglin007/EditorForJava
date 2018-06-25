package com.xiaomi.editor.service;

import com.xiaomi.editor.bean.NoticeBean;

/**
 * Description:公告 <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-25<br>
 * Time: 11:31<br>
 * UpdateDescription：<br>
 */
public interface INoticeBeanService {

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

}
