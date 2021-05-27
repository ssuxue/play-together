package com.chase.play.controller;

import com.chase.play.common.api.CommonResult;
import com.chase.play.model.ExtensionEvaluation;
import com.chase.play.service.EvaluationService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/25 20:25
 */
@RestController
@Api(tags = "活动评价管理")
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/getOne/{eid}")
    @ApiOperation("获取活动的评价信息")
    public CommonResult getEvaluationInfo(@ApiParam("活动ID") @PathVariable("eid") Integer eid) {
        return CommonResult.success(evaluationService.getOne(eid));
    }

    @PostMapping("/commit/{eid}/{eval}")
    @ApiOperation("提交评价")
    public CommonResult commitEvaluation(@PathVariable("eid") Integer eid,
                                         @PathVariable("eval")BigDecimal eval) {

        ExtensionEvaluation evaluation = new ExtensionEvaluation();
        evaluation.setEid(eid);
        evaluation.setEvaluation(eval);

        boolean result = evaluationService.calculate(evaluation);
        return result ? CommonResult.success("评价成成功") : CommonResult.failed();
    }
}
