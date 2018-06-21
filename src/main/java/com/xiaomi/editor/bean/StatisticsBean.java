package com.xiaomi.editor.bean;

import java.util.Date;

/**
 * 统计表
 */
public class StatisticsBean {
    /**
     * id
     */
    private Integer statisticsId;
    /**
     * 上次统计时间
     */
    private Date statisticsTime;
    /**
     * 统计类型（0:工作室、1:服务）
     */
    private Short statisticsType;

    /**
     * 工作室/服务的id
     */
    private Integer contentId;

    /**
     * 工作室/服务销售总数量
     */
    private Integer contentSumNumber;

    /**
     * 当前工作室/服务的好评数
     */
    private Integer contentSumScore;

    /**
     * 当前工作室/服务的当前月累积销量
     */
    private Integer contentSumNumberMonth;

    public StatisticsBean(Integer statisticsId, Date statisticsTime, Short statisticsType, Integer contentId, Integer contentSumNumber, Integer contentSumScore, Integer contentSumNumberMonth) {
        this.statisticsId = statisticsId;
        this.statisticsTime = statisticsTime;
        this.statisticsType = statisticsType;
        this.contentId = contentId;
        this.contentSumNumber = contentSumNumber;
        this.contentSumScore = contentSumScore;
        this.contentSumNumberMonth = contentSumNumberMonth;
    }

    public StatisticsBean() {
        super();
    }

    public Integer getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(Integer statisticsId) {
        this.statisticsId = statisticsId;
    }

    public Date getStatisticsTime() {
        return statisticsTime;
    }

    public void setStatisticsTime(Date statisticsTime) {
        this.statisticsTime = statisticsTime;
    }

    public Short getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(Short statisticsType) {
        this.statisticsType = statisticsType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentSumNumber() {
        return contentSumNumber;
    }

    public void setContentSumNumber(Integer contentSumNumber) {
        this.contentSumNumber = contentSumNumber;
    }

    public Integer getContentSumScore() {
        return contentSumScore;
    }

    public void setContentSumScore(Integer contentSumScore) {
        this.contentSumScore = contentSumScore;
    }

    public Integer getContentSumNumberMonth() {
        return contentSumNumberMonth;
    }

    public void setContentSumNumberMonth(Integer contentSumNumberMonth) {
        this.contentSumNumberMonth = contentSumNumberMonth;
    }
}