package com.microsoft.bean;

/**
 * @author Created by huiliu on 2018/12/6.
 * @email liu594545591@126.com
 * @introduce
 */
public class PropertyBean {

    private int limit;
    private int offset;
    private String taskId;
    private String task_name;
    private String tb;
    private String created_at;
    private String updated_at;
    private String status;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PropertyBean{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", taskId='" + taskId + '\'' +
                ", task_name='" + task_name + '\'' +
                ", tb='" + tb + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
