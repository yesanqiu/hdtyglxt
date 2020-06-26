package com.example.hdtyglxt.config;

public enum ErrorCodeMsg {

    REGISTER_FAILURE("201","注册失败"),
    USERNAME_USED("202","用户名已被注册"),
    STUDENT_ID_USED("203","此学号已注册"),
    LOGIN_FAILURE("204","登陆失败"),
    UNLOGIN("205","未登录，请先登录"),
    USER_NO_EXIST("206","用户不存在"),
    PASSWORD_NO_TRUE("207","密码不正确"),
    AUTHORIT_OUT_OF_BOUND("208","权限不够");

    private String code;
    private String msg;
    private ErrorCodeMsg(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

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
}
