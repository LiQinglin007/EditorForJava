package com.xiaomi.editor.paramsbean;

/**
 * Description: 分页查询<br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-29<br>
 * Time: 14:08<br>
 * UpdateDescription：<br>
 */
public class PageListBean {
    int page;
    int size;

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

    public PageListBean(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
