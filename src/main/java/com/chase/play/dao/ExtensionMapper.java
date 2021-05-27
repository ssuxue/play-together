package com.chase.play.dao;

import com.chase.play.dto.ExtensionDTO;
import com.chase.play.model.Extension;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/24 10:48
 */
public interface ExtensionMapper {

    /**
     * 查询所有活动
     * @return 活动列表
     */
    List<Extension> selectAll();

    /**
     * 添加活动
     * @param extension 活动信息
     * @return 添加状态 0 -> 失败 :: 大于0 -> 成功
     */
    int add(Extension extension);

    /**
     * 根据id查询活动
     * @param eid 活动id
     * @return 查到的活动
     */
    Extension select(Integer eid);

    /**
     * 根据标签类型查询
     * @param type 类型
     * @return 活动
     */
    List<Extension> selectByTag(Integer type);

    /**
     * 结束活动
     * @param eid 活动ID
     * @return 操作结果
     */
    int overExtension(Integer eid);

    /**
     * 活动成员数量修改
     * @param eid 活动ID
     * @return 结果
     */
    int updateMemberNumber(Integer eid);

    /**
     * 成员退出活动-->数量减少
     * @param eid 活动ID
     * @return 结果
     */
    int creaseMemberNumber(Integer eid);

    /**
     * 通过状态获取活动
     * @param state 状态
     * @return 获取的活动
     */
    List<Extension> getByState(Integer state);

    /**
     * 获取指定用户参加过的已经结束的活动
     * @param uid 状态
     * @return 获取的活动
     */
    List<Extension> getOverByUid(Integer uid);

    /**
     * 根据状态获取指定用户创建的活动
     * @param uid 用户ID
     * @return 活动
     */
    List<Extension> getRunningByUid(Integer uid);

    /**
     * 获取用户创建的活动
     * @param uid 用户ID
     * @return 活动
     */
    List<Extension> getByUid(Integer uid);

    /**
     * 获取用户参与的活动
     * @param uid
     * @return
     */
    List<Extension> getJoinExtensionByUid(Integer uid);

    /**
     * 删除活动
     * @param eid 活动ID
     * @return
     */
    int delete(Integer eid);

    /**
     * 获取活动事件和ID
     * @return
     */
    List<ExtensionDTO> getTimeAndId();

    /**
     * 用来解决一个Bug -> 可能出现活动成员数量为负数的情况，就将其设为1个成员
     * @param eid
     */
    void setJoinNumToOne(Integer eid);
}
