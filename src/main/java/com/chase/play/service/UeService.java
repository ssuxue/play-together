package com.chase.play.service;

import com.chase.play.model.Extension;
import com.chase.play.model.Ue;
import com.chase.play.model.User;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/24 12:00
 */
public interface UeService {

    /** 增加UE **/
    boolean add(Integer uid, Integer eid);

    /** 根据UID删除UE **/
    boolean deleteByUid(Integer uid);

    /** 根据EID删除UE **/
    boolean deleteByEid(Integer eid);

    /**
     * 退出活动
     * @param uid 用户ID
     * @param eid 活动ID
     * @return 操作结果
     */
    boolean exitExtension(Integer uid, Integer eid);

    List<User> getAttendUser(Integer eid);
}
