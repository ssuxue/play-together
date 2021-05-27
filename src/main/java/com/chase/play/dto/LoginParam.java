package com.chase.play.dto;

/**
 * @version 1.0
 * @Description 登录请求数据
 * @Author chase
 * @Date 2020/9/21 23:25
 */
public class LoginParam {
    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
