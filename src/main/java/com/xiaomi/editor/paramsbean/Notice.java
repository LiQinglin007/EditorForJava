package com.xiaomi.editor.paramsbean;

import java.util.Date;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-29<br>
 * Time: 14:28<br>
 * UpdateDescription：<br>
 */
public class Notice {
    /**
     * id
     */
    private Integer noticeId;

    /**
     * 公告id
     */
    private String noticeTitle;

    /**
     * 内容
     */
    private String noticeContent;

    /**
     * 时间
     */
    private Date noticeTime;

    /**
     * 是否删除   0：不被删除  1：被删除了
     */
    private Short noticeDel;


    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Short getNoticeDel() {
        return noticeDel;
    }

    public void setNoticeDel(Short noticeDel) {
        this.noticeDel = noticeDel;
    }
}
