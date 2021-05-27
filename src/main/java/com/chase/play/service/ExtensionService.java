package com.chase.play.service;

import com.chase.play.model.Extension;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/24 11:29
 */
public interface ExtensionService {

    /** 通过活动ID查询活动 **/
    Extension getByEid(Integer eid);

    /** 通过活动标签获取活动 **/
    List<Extension> getByTag(Integer tag);

    /** 获取所有活动 **/
    List<Extension> getAll();

    /** 新增活动 **/
    int add(Extension extension);

    /** 修改活动状态 **/
    boolean updateState(Integer eid);

    /** 修改活动人数 **/
    void updateMemberNumber(Integer eid);

    /** 修改活动人数 **/
    void creaseMemberNumber(Integer eid);

    /** 根据状态获取活动 **/
    List<Extension> getByState(Integer state);

    /** 获取用户创建活动 **/
    List<Extension> getByUid(Integer uid);

    /** 获取用户加入且正在进行的活动 **/
    List<Extension> getRunningByUid(Integer uid);

    /** 获取用户加入的活动 **/
    List<Extension> getJoinExtensionByUid(Integer uid);

    /** 删除活动 **/
    List<Extension> getOverExtension(Integer uid);

    /** 删除活动 **/
    boolean delete(Integer eid);

    /** 获取活动ID和时间 **/
    HashSet<Integer> getIds() throws ParseException;

    /** 结束活动 **/
    void stopExtension(HashSet<Integer> ids);
}
