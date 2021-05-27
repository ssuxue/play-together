package com.chase.play.dao;

import com.chase.play.model.Ue;
import com.chase.play.model.User;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/24 10:48
 */
public interface UeMapper {

    /**
     * 增加UE
     * @param ue
     * @return
     */
    int add(Ue ue);

    /**
     * 根据UID删除UE
     * @param uid 用户ID
     * @return 操作结果
     */
    int deleteByUid(Integer uid);

    /**
     * 根据EID删除UE
     * @param eid 活动ID
     * @return 结果
     */
    int deleteByEid(Integer eid);

    /**
     * 退出活动
     * @param uid 用户ID
     * @param eid 活动ID
     * @return 退出结果
     */
    int delete(Integer uid, Integer eid);

    /**
     * 获取参与用户
     * @param eid
     * @return
     */
    List<User> getAttendUser(Integer eid);

}
