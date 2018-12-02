package com.microsoft.bean;

/**
 * Created by huiliu on 2018/11/27.
 *
 * @email liu594545591@126.com
 * @introduce  手机验证码
 */
public class MobileCode {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MobileCode{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
