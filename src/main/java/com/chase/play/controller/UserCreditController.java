package com.chase.play.controller;

import com.chase.play.common.api.CommonResult;
import com.chase.play.model.UserCredit;
import com.chase.play.service.UserCreditService;
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
@Api(tags = "用户信誉管理")
@RequestMapping("/credit")
public class UserCreditController {

    @Autowired
    private UserCreditService userCreditService;

    @GetMapping("/getOne/{uid}")
    @ApiOperation("获取用户信誉")
    public CommonResult getEvaluationInfo(@ApiParam("用户ID") @PathVariable("uid") Integer uid) {
        return CommonResult.success(userCreditService.getOne(uid));
    }

    @PostMapping("/commit")
    @ApiOperation("评价用户")
    public CommonResult commitEvaluation(@ApiParam("活动信息") @RequestParam("extension") String userCreditString) {
        Gson gson = new Gson();
        UserCredit userCredit = gson.fromJson(userCreditString, UserCredit.class);
        boolean result = userCreditService.calculate(userCredit);
        return result ? CommonResult.success("评价成功") : CommonResult.failed();
    }

    @PostMapping("/report/{uid}")
    @ApiOperation("举报用户")
    public CommonResult ReportUser(@ApiParam("用户ID") @PathVariable("uid") Integer uid) {
        UserCredit userCredit = new UserCredit();
        userCredit.setUid(uid);
        userCredit.setCredit(BigDecimal.valueOf(0));
        boolean result = userCreditService.calculate(userCredit);
        return result ? CommonResult.success("举报成功") : CommonResult.failed();
    }

}
