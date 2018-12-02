package com.microsoft.bean;

/**
 * @author Created by huiliu on 2018/11/28.
 * @email liu594545591@126.com
 * @introduce
 */
public class TaskDetailRootBean {
    private String msg;
    private String code;
    private TaskDetailMiddleBean data;

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

    public TaskDetailMiddleBean getData() {
        return data;
    }

    public void setData(TaskDetailMiddleBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TaskDetailRootBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
