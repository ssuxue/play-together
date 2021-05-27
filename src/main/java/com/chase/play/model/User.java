package com.chase.play.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 命名不规范实属无奈  为了跟着移动端改的
 * @version 1.0
 * @Author chase
 * @Date 2020/9/22 12:39
 */
public class User implements Serializable {

    private Integer uid;
    @NotNull(message = "密码不能为空！")
    private String pwd;
    private String userName;
    private Integer joinNum;
    private Integer createNum;
    private Integer state;
    @NotNull(message = "邮箱不能为空！")
    @Email(message = "邮箱不能为空！！")
    private String email;
    private String avatar;
    private String sid;

    public Integer getUid() {
        return uid;
    }

    public String getPwd() {
        return pwd;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getJoinNum() {
        return joinNum;
    }

    public Integer getCreateNum() {
        return createNum;
    }

    public Integer getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getSid() {
        return sid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    public void setCreateNum(Integer createNum) {
        this.createNum = createNum;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", pwd='" + pwd + '\'' +
                ", userName='" + userName + '\'' +
                ", joinNum=" + joinNum +
                ", createNum=" + createNum +
                ", state=" + state +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sid='" + sid + '\'' +
                '}';
    }
}
