package com.xiaomi.editor.bean;

/**
 * 收藏表
 */
public class CollectionBean {
    /**
     * 收藏id
     */
    private Short collectionId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收藏类型（0:工作室、1:服务）
     */
    private Short collectionType;

    /**
     * 收藏内容的id
     */
    private Integer collectionContentId;

    public CollectionBean(Short collectionId, Integer userId, Short collectionType, Integer collectionContentId) {
        this.collectionId = collectionId;
        this.userId = userId;
        this.collectionType = collectionType;
        this.collectionContentId = collectionContentId;
    }

    public CollectionBean() {
        super();
    }

    public Short getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Short collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Short getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(Short collectionType) {
        this.collectionType = collectionType;
    }

    public Integer getCollectionContentId() {
        return collectionContentId;
    }

    public void setCollectionContentId(Integer collectionContentId) {
        this.collectionContentId = collectionContentId;
    }
}