package com.microsoft.bean;

import java.util.List;

/**
 * @author Created by huiliu on 2018/12/6.
 * @email liu594545591@126.com
 * @introduce
 */
public class PropertyListBean {
    private List<String> arr_status;
    private int size;
    private int page;
    private List<PropertyBean> logs;

    public List<String> getArr_status() {
        return arr_status;
    }

    public void setArr_status(List<String> arr_status) {
        this.arr_status = arr_status;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<PropertyBean> getLogs() {
        return logs;
    }

    public void setLogs(List<PropertyBean> logs) {
        this.logs = logs;
    }
}
