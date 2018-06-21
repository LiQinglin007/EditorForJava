package com.xiaomi.editor.bean;

import java.util.Date;

public class OrderBean {
    private Integer orderId;

    private Short orderType;

    private Short orderState;

    private Integer studioId;

    private Integer servletId;

    private Integer resultFileId;

    private Integer userFileId;

    private Integer userId;

    private Float orderPrice;

    private String orderResult;

    private String orderSumTime;

    private Date createTime;

    private Date receiptTime;

    private Date confirmTime;

    private Date evaluateTime;

    public OrderBean(Integer orderId, Short orderType, Short orderState, Integer studioId, Integer servletId, Integer resultFileId, Integer userFileId, Integer userId, Float orderPrice, String orderResult, String orderSumTime, Date createTime, Date receiptTime, Date confirmTime, Date evaluateTime) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.orderState = orderState;
        this.studioId = studioId;
        this.servletId = servletId;
        this.resultFileId = resultFileId;
        this.userFileId = userFileId;
        this.userId = userId;
        this.orderPrice = orderPrice;
        this.orderResult = orderResult;
        this.orderSumTime = orderSumTime;
        this.createTime = createTime;
        this.receiptTime = receiptTime;
        this.confirmTime = confirmTime;
        this.evaluateTime = evaluateTime;
    }

    public OrderBean() {
        super();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Short getOrderType() {
        return orderType;
    }

    public void setOrderType(Short orderType) {
        this.orderType = orderType;
    }

    public Short getOrderState() {
        return orderState;
    }

    public void setOrderState(Short orderState) {
        this.orderState = orderState;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public Integer getServletId() {
        return servletId;
    }

    public void setServletId(Integer servletId) {
        this.servletId = servletId;
    }

    public Integer getResultFileId() {
        return resultFileId;
    }

    public void setResultFileId(Integer resultFileId) {
        this.resultFileId = resultFileId;
    }

    public Integer getUserFileId() {
        return userFileId;
    }

    public void setUserFileId(Integer userFileId) {
        this.userFileId = userFileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderResult() {
        return orderResult;
    }

    public void setOrderResult(String orderResult) {
        this.orderResult = orderResult == null ? null : orderResult.trim();
    }

    public String getOrderSumTime() {
        return orderSumTime;
    }

    public void setOrderSumTime(String orderSumTime) {
        this.orderSumTime = orderSumTime == null ? null : orderSumTime.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }
}