package com.xiaomi.editor.paramsbean;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-05<br>
 * Time: 17:29<br>
 * UpdateDescription：<br>
 */
public class PageListBeanSearch extends PageListBean {
    private String searchContent;

    public PageListBeanSearch(int page, int size, String content) {
        super(page, size);
        this.searchContent = content;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
