package com.chase.play.dao;

import com.chase.play.model.UserCredit;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/25 19:32
 */
public interface UserCreditMapper {

    /**
     * 获取用户信誉信息
     * @param uid 用户ID
     * @return 信誉信息
     */
     UserCredit select(Integer uid);

    /**
     * 更新活动评价表信息
     * @return 结果
     */
    int update(UserCredit userCredit);

}
