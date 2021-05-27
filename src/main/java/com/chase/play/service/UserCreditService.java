package com.chase.play.service;

import com.chase.play.model.UserCredit;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/25 20:02
 */
public interface UserCreditService {

    /**
     * 获取用户信誉信息
     */
    UserCredit getOne(Integer uid);

    /**
     * 计算信誉分和更新评价人数
     */
    boolean calculate(UserCredit userCredit);
}
