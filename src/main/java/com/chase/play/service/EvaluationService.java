package com.chase.play.service;

import com.chase.play.model.ExtensionEvaluation;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/25 20:02
 */
public interface EvaluationService {

    /**
     * 获取活动评价信息
     */
    ExtensionEvaluation getOne(Integer eid);

    /**
     * 计算评分和更新评价人数
     */
    boolean calculate(ExtensionEvaluation evaluation);
}
