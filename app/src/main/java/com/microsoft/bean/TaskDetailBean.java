package com.microsoft.bean;

/**
 * @author Created by huiliu on 2018/11/28.
 * @email liu594545591@126.com
 * @introduce
 */
public class TaskDetailBean {

    private String img;
    private String checktime;
    private String author;
    private int bonus;
    private String created_at;
    private String title;
    private int type;
    private String content;
    private int brower;
    private String jx_effectbegin;
    private String updated_at;
    private int limitsum;
    private String taskId;
    private int status;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBrower() {
        return brower;
    }

    public void setBrower(int brower) {
        this.brower = brower;
    }

    public String getJx_effectbegin() {
        return jx_effectbegin;
    }

    public void setJx_effectbegin(String jx_effectbegin) {
        this.jx_effectbegin = jx_effectbegin;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getLimitsum() {
        return limitsum;
    }

    public void setLimitsum(int limitsum) {
        this.limitsum = limitsum;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskDetailBean{" +
                "img='" + img + '\'' +
                ", checktime='" + checktime + '\'' +
                ", author='" + author + '\'' +
                ", bonus=" + bonus +
                ", created_at='" + created_at + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", brower=" + brower +
                ", jx_effectbegin='" + jx_effectbegin + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", limitsum=" + limitsum +
                ", taskId='" + taskId + '\'' +
                ", status=" + status +
                '}';
    }
}
