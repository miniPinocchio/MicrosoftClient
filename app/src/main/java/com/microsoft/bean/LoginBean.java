package com.microsoft.bean;

/**
 * @author Created by huiliu on 2018/11/27.
 * @email liu594545591@126.com
 * @introduce
 */
public class LoginBean {
    private String password;
    private int money;
    private String mobile;
    private String name;
    private String id;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "password='" + password + '\'' +
                ", money=" + money +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
