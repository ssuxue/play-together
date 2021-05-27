package com.chase.play.controller;

import com.chase.play.common.api.CommonResult;
import com.chase.play.model.Extension;
import com.chase.play.model.User;
import com.chase.play.service.UeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/24 12:07
 */
@RestController
@Api(tags = "UE管理")
@RequestMapping("/ue")
public class UeController {

    @Autowired
    private UeService ueService;

    @GetMapping("/add/{uid}/{eid}")
    public CommonResult add(@PathVariable("uid") Integer uid,
                            @PathVariable("eid") Integer eid) {
        boolean result = ueService.add(uid, eid);
        if (result) {
            return  CommonResult.success("添加成功");
        } else {
            return CommonResult.failed();
        }
    }

    @DeleteMapping("/deleteByUid/{uid}")
    public CommonResult deleteByUid(@PathVariable("uid") Integer uid) {
        boolean delete = ueService.deleteByUid(uid);
        return delete ? CommonResult.success("取消成功") : CommonResult.failed();
    }

    @PostMapping("/deleteByEid/{eid}")
    public CommonResult deleteByEid(@PathVariable("eid") Integer eid) {
        boolean delete = ueService.deleteByEid(eid);
        return delete ? CommonResult.success("修改成功") : CommonResult.failed();
    }

    @PostMapping("/exit/{uid}/{eid}")
    public CommonResult exitExtension(@PathVariable("uid") Integer uid,
                                      @PathVariable("eid") Integer eid) {
        boolean result = ueService.exitExtension(uid, eid);
        return result ? CommonResult.success("退出成功") : CommonResult.failed();
    }

    @ApiOperation("获取活动的人员")
    @GetMapping("/getAttendUser/{eid}")
    public CommonResult<List<User>> getAttendUser(@ApiParam("活动ID") @PathVariable("eid") Integer eid) {
        return CommonResult.success(ueService.getAttendUser(eid));
    }
}
