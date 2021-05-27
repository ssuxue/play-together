package com.chase.play.service;

import com.chase.play.model.User;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/9/22 22:21
 */
public interface AdminService {

    /**
     * 登录验证
     *
     * @param email    邮箱
     * @param password 密码
     * @return
     */
    User login(String email, String password);

    /**
     * 注册账号
     *
     * @param user 用户信息
     * @return 注册结果 -> true:成功 false:失败
     */
    boolean registry(User user);

    /**
     * 生成验证码
     *
     * @param email 邮箱
     * @return 验证码
     */
    String generateCaptcha(String email);

    /**
     * 判断验证码和手机号码是否匹配
     *
     * @param email   邮箱
     * @param captcha 验证码
     * @return 验证结果
     */
    boolean verifyCaptcha(String email, String captcha);

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return User
     */
    User getUser(String email);

    /**
     * 修改用户信息
     *
     * @param user 新的用户信息
     * @return 修改结果  true:成功
     */
    boolean updateUserInfo(User user);

    /**
     * 根据学号判断用户是否进行过学生认证
     *
     * @param sid 学号
     * @return User
     */
    boolean validateSid(String sid);

}
