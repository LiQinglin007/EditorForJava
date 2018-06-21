package com.xiaomi.editor.bean;

import java.util.Date;

/**
 * 公告
 */
public class NoticeBean {
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

    public NoticeBean(Integer noticeId, String noticeTitle, String noticeContent, Date noticeTime) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeTime = noticeTime;
    }

    public NoticeBean() {
        super();
    }

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
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }
}