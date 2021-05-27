package com.chase.play.dao;

import com.chase.play.model.ExtensionEvaluation;

import java.math.BigDecimal;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/25 19:32
 */
public interface EvaluationMapper {

    /**
     * 获取评价信息
     * @param eid 活动ID
     * @return 评价信息
     */
     ExtensionEvaluation select(Integer eid);

    /**
     * 获取评价分
     * @param eid
     * @return
     */
     BigDecimal selectScore(Integer eid);

    /**
     * 更新活动评价表信息
     * @return 结果
     */
    int update(ExtensionEvaluation evaluation);

    /**
     * 插入活动评价信息
     * @param eid 活动ID
     */
    void insert(Integer eid);

    /**
     * 删除活动
     */
    void delete(Integer eid);

}
