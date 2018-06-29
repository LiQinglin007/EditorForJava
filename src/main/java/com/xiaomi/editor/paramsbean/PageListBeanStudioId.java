package com.xiaomi.editor.paramsbean;

/**
 * Description:按工作室id分页查询 <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-29<br>
 * Time: 14:12<br>
 * UpdateDescription：<br>
 */
public class PageListBeanStudioId extends PageListBean {
    private int StudioId;

    public PageListBeanStudioId(int page, int size) {
        super(page, size);
    }

    public int getStudioId() {
        return StudioId;
    }

    public void setStudioId(int studioId) {
        StudioId = studioId;
    }
}
