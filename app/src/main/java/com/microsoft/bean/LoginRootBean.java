package com.microsoft.bean;

/**
 * @author Created by huiliu on 2018/11/27.
 * @email liu594545591@126.com
 * @introduce
 */
public class LoginRootBean {

    private String msg;
    private String code;
    private LoginBean app_user;

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

    public LoginBean getApp_user() {
        return app_user;
    }

    public void setApp_user(LoginBean app_user) {
        this.app_user = app_user;
    }

    @Override
    public String toString() {
        return "LoginRootBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", app_user=" + app_user +
                '}';
    }
}
