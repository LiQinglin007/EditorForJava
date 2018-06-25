package com.xiaomi.editor.bean;

public class UserfileBean {
    private Integer fileId;

    private Integer userId;

    private String fileName;

    private String fileUrl;

    public UserfileBean(Integer fileId, Integer userId, String fileName, String fileUrl) {
        this.fileId = fileId;
        this.userId = userId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    public UserfileBean() {
        super();
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }
}