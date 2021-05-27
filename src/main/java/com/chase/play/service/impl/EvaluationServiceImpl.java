package com.chase.play.service.impl;

import com.chase.play.dao.EvaluationMapper;
import com.chase.play.dao.ExtensionMapper;
import com.chase.play.dao.UserCreditMapper;
import com.chase.play.model.Extension;
import com.chase.play.model.ExtensionEvaluation;
import com.chase.play.model.UserCredit;
import com.chase.play.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/25 20:04
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;
    @Autowired
    private UserCreditMapper userCreditMapper;
    @Autowired
    private ExtensionMapper extensionMapper;

    @Override
    public ExtensionEvaluation getOne(Integer eid) {
        return evaluationMapper.select(eid);
    }

    @Override
    public boolean calculate(ExtensionEvaluation evaluation) {
        Integer eid = evaluation.getEid();
        ExtensionEvaluation extensionEvaluation = evaluationMapper.select(eid);

        int num  = extensionEvaluation.getEvalNum();
        double value = extensionEvaluation.getEvaluation().doubleValue() * num + evaluation.getEvaluation().doubleValue();
        double score = value / (num + 1);
        extensionEvaluation.setEvaluation(BigDecimal.valueOf(score));

        int update1 = evaluationMapper.update(extensionEvaluation);

        // 影响用户信誉
        Extension extension = extensionMapper.select(eid);
        UserCredit user = userCreditMapper.select(extension.getUid());
        int evalNum = user.getEvalNum();
        double value1 = user.getCredit().doubleValue() * evalNum + evaluation.getEvaluation().doubleValue();
        BigDecimal credit = new BigDecimal(String.valueOf((value1 / (evalNum + 1))));
        user.setCredit(credit);

        int update2 = userCreditMapper.update(user);

        return (update1 + update2) > 1;
    }
}
