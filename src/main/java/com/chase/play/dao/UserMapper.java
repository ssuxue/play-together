package com.chase.play.dao;

import com.chase.play.model.User;

import java.util.List;

/**
 * @version 1.0
 * @Author chase
 * @Date 2020/9/22 12:37
 */
public interface UserMapper {

    /**
     * 根据邮箱和密码查询用户
     * @param email 邮箱
     * @param password 密码
     * @return 登录用户参数
     */
    User getLoginParam(String email, String password);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> selectAll();

    /**
     * 添加用户
     * @param user 用户信息
     * @return 添加状态 0 -> 失败 :: 大于0 -> 成功
     */
    int add(User user);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 查到的用户
     */
    User select(String email);

    /**
     * 根据学号查询用户
     * @param sid 学号
     * @return 查到的用户
     */
    User selectBySid(String sid);

    /**
     * 修改密码
     * @param user 用户信息
     * @return 修改成功记录
     */
    int modify(User user);

    /**
     * 根据ID查询用户信息
     * @param uid　用户ID
     * @return　用户信息
     */
    User selectByUid(Integer uid);

    /**
     * 添加用户初始信誉
     * @param uid 用户ID
     */
    void addUserCredit(Integer uid);
}
