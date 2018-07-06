package com.xiaomi.editor.paramsbean;

/**
 * Description:按工作室id分页查询 <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-29<br>
 * Time: 14:12<br>
 * UpdateDescription：<br>
 */
public class PageListBeanStudioId  {
    private int StudioId;
    private int page;
    private int size;

    public PageListBeanStudioId(int studioId, int page, int size) {
        StudioId = studioId;
        this.page = page;
        this.size = size;
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

    public int getStudioId() {
        return StudioId;
    }

    public void setStudioId(int studioId) {
        StudioId = studioId;
    }
}
