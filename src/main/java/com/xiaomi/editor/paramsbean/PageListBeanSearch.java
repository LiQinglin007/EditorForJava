package com.xiaomi.editor.paramsbean;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-05<br>
 * Time: 17:29<br>
 * UpdateDescription：<br>
 */
public class PageListBeanSearch   {
    private int page;
    private int size;
    private String searchContent;

    public PageListBeanSearch(int page, int size, String searchContent) {
        this.page = page;
        this.size = size;
        this.searchContent = searchContent;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
