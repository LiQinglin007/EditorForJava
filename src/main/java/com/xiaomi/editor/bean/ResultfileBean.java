package com.xiaomi.editor.bean;

import java.util.Date;

/**
 * 检查结束后的文件
 */
public class ResultfileBean {
    /**
     * 文件id
     */
    private Integer fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 作者
     */
    private String author;

    /**
     * 作者学历
     */
    private String authorDegree;

    /**
     * 下载地址
     */
    private String downloadurl;

    /**
     * 见刊时间
     */
    private Date seeTime;

    public ResultfileBean(Integer fileId, String fileName, String author, String authorDegree, String downloadurl, Date seeTime) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.author = author;
        this.authorDegree = authorDegree;
        this.downloadurl = downloadurl;
        this.seeTime = seeTime;
    }

    public ResultfileBean() {
        super();
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getAuthorDegree() {
        return authorDegree;
    }

    public void setAuthorDegree(String authorDegree) {
        this.authorDegree = authorDegree == null ? null : authorDegree.trim();
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl == null ? null : downloadurl.trim();
    }

    public Date getSeeTime() {
        return seeTime;
    }

    public void setSeeTime(Date seeTime) {
        this.seeTime = seeTime;
    }
}