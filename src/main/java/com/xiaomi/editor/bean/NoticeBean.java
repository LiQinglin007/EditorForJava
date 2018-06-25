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

    /**
     * 是否删除   0：不被删除  1：被删除了
     */
    private Short noticeDel;


    public NoticeBean(Integer noticeId, String noticeTitle, String noticeContent, Date noticeTime, Short noticeDel) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeTime = noticeTime;
        this.noticeDel = noticeDel;
    }


    public NoticeBean(String noticeTitle, String noticeContent, Date noticeTime) {
        this.noticeId = null;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeTime = noticeTime;
        this.noticeDel = 0;
    }

    public NoticeBean() {
        super();
    }

    public Short getNoticeDel() {
        return noticeDel;
    }

    public void setNoticeDel(Short noticeDel) {
        this.noticeDel = noticeDel;
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