package com.microsoft.bean;

/**
 * @author Created by huiliu on 2018/11/27.
 * @email liu594545591@126.com
 * @introduce
 */
public class DyRootBean {

    private String msg;
    private String code;
    private DyMiddleBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DyMiddleBean getData() {
        return data;
    }

    public void setData(DyMiddleBean data) {
        this.data = data;
    }
}
