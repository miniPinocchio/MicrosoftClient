package com.microsoft.bean;

/**
 * @author Created by huiliu on 2018/11/28.
 * @email liu594545591@126.com
 * @introduce
 */
public class TaskDetailMiddleBean {

    private TaskDetailBean regTask;
    public void setTaskDetailBean(TaskDetailBean regTask) {
        this.regTask = regTask;
    }
    public TaskDetailBean getTaskDetailBean() {
        return regTask;
    }


    @Override
    public String toString() {
        return "TaskDetailMiddleBean{" +
                "regTask=" + regTask +
                '}';
    }
}
