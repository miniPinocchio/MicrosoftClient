package com.microsoft.bean;

/**
 * @author Created by huiliu on 2018/12/6.
 * @email liu594545591@126.com
 * @introduce
 */
public class PropertyRoot {

    private String msg;
    private String code;
    private PropertyListBean data;

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

    public PropertyListBean getData() {
        return data;
    }

    public void setData(PropertyListBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PropertyRoot{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
