package com.xiaomi.editor.bean;

import java.util.Date;

/**
 * 反馈表
 */
public class FeedbackBean {
    /**
     * 反馈id
     */
    private Short feedbackId;

    /**
     * 反馈类型(0:意见 ; 1：bug)
     */
    private Short feedbackType;

    /**
     * 反馈内容
     */
    private String feedbackContent;

    /**
     * 反馈用户id
     */
    private Integer feedbackUserid;

    /**
     * 反馈时间
     */
    private Date feedbackTime;

    public FeedbackBean(Short feedbackId, Short feedbackType, String feedbackContent, Integer feedbackUserid, Date feedbackTime) {
        this.feedbackId = feedbackId;
        this.feedbackType = feedbackType;
        this.feedbackContent = feedbackContent;
        this.feedbackUserid = feedbackUserid;
        this.feedbackTime = feedbackTime;
    }

    public FeedbackBean() {
        super();
    }

    public Short getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Short feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Short getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(Short feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent == null ? null : feedbackContent.trim();
    }

    public Integer getFeedbackUserid() {
        return feedbackUserid;
    }

    public void setFeedbackUserid(Integer feedbackUserid) {
        this.feedbackUserid = feedbackUserid;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }
}