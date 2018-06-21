package com.xiaomi.editor.bean;

import java.util.Date;

/**
 * 评论
 */
public class EvaluateBean {
    /**
     * id
     */
    private Integer evaluateId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 评论内容
     */
    private String evaluateConetnt;

    /**
     * 时间
     */
    private Date evaluateTime;

    /**
     * 评分  1:好评  2：差评
     */
    private Short evaluateScore;

    public EvaluateBean(Integer evaluateId, Integer orderId, String evaluateConetnt, Date evaluateTime, Short evaluateScore) {
        this.evaluateId = evaluateId;
        this.orderId = orderId;
        this.evaluateConetnt = evaluateConetnt;
        this.evaluateTime = evaluateTime;
        this.evaluateScore = evaluateScore;
    }

    public EvaluateBean() {
        super();
    }

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getEvaluateConetnt() {
        return evaluateConetnt;
    }

    public void setEvaluateConetnt(String evaluateConetnt) {
        this.evaluateConetnt = evaluateConetnt == null ? null : evaluateConetnt.trim();
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public Short getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(Short evaluateScore) {
        this.evaluateScore = evaluateScore;
    }
}