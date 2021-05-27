package com.chase.play.common.api;

/**
 * @version 1.0
 * @Description 发送邮件的用途
 * @Author chase
 * @Date 2020/9/27 8:27
 */
public enum EmailPurposeEnum {
    /** 注册请求 **/
    REGISTER("register", "registry"),
    /** 找回密码 **/
    RETRIEVE("retrieve", "pwdBack");

    private String param;
    private String purpose;

    EmailPurposeEnum(String param, String purpose) {
        this.param = param;
        this.purpose = purpose;
    }

    public String getParam() {
        return param;
    }

    public String getPurpose() {
        return purpose;
    }
}
