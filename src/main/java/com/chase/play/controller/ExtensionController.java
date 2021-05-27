package com.chase.play.controller;

import com.chase.play.common.api.CommonResult;
import com.chase.play.model.Extension;
import com.chase.play.service.ExtensionService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/24 11:27
 */
@RestController
@Api(tags = "活动管理")
@RequestMapping("/extension")
public class ExtensionController {

    @Autowired
    private ExtensionService extensionService;

    @GetMapping("/getByEid/{eid}")
    public CommonResult<Extension> getByEid(@PathVariable("eid") Integer eid) {
        Extension extension = extensionService.getByEid(eid);
        return CommonResult.success(extension);
    }

    @GetMapping("/getByTag/{tag}")
    public CommonResult<List<Extension>> getByTag(@PathVariable("tag") Integer tag) {
        List<Extension> extensionList = extensionService.getByTag(tag);
        return CommonResult.success(extensionList);
    }

    @GetMapping("/getAll")
    public CommonResult<List<Extension>> getAll() {
        return CommonResult.success(extensionService.getAll());
    }

    @PostMapping("/save")
    public CommonResult save(@ApiParam("活动信息") @RequestParam("extension") String extensionString) {
        Gson gson = new Gson();
        Extension extension = gson.fromJson(extensionString, Extension.class);
        int eid = extensionService.add(extension);
        if (eid == 0) {
            return CommonResult.failed();
        }
        return CommonResult.success(eid);
    }

    @PostMapping("/over/{eid}")
    public CommonResult overExtension(@PathVariable("eid") Integer eid) {
        boolean update = extensionService.updateState(eid);
        return update ? CommonResult.success("取消成功") : CommonResult.failed();
    }

    @GetMapping("/getByState/{state}")
    @ApiOperation("根据状态获取活动")
    public CommonResult<List<Extension>> getByState(@ApiParam("活动状态") @PathVariable("state") Integer state) {
        List<Extension> extensionList = extensionService.getByState(state);
        return CommonResult.success(extensionList);
    }

    @GetMapping("/getByUid/{uid}")
    @ApiOperation("获取用户创建的活动")
    public CommonResult<List<Extension>> getByUid(@ApiParam("用户ID") @PathVariable("uid") Integer uid) {
        List<Extension> extensionList = extensionService.getByUid(uid);
        return CommonResult.success(extensionList);
    }

    @GetMapping("/getRunningByUid/{uid}")
    @ApiOperation("获取用户加入的正在进行的活动")
    public CommonResult<List<Extension>> getRunningByUid(@ApiParam("用户ID") @PathVariable("uid") Integer uid) {
        List<Extension> extensionList = extensionService.getRunningByUid(uid);
        return CommonResult.success(extensionList);
    }

    @GetMapping("/getJoinByUid/{uid}")
    @ApiOperation("获取用户参与的活动")
    public CommonResult<List<Extension>> getJoinExtensionByUid(@ApiParam("用户ID") @PathVariable("uid") Integer uid) {
        List<Extension> extensionList = extensionService.getJoinExtensionByUid(uid);
        return CommonResult.success(extensionList);
    }

    @GetMapping("/getOverExtension/{uid}")
    @ApiOperation("获取用户参与的已结束的活动")
    public CommonResult<List<Extension>> getOverExtension(@PathVariable("uid") Integer uid) {
        List<Extension> overExtension = extensionService.getOverExtension(uid);
        return CommonResult.success(overExtension);
    }

    @PostMapping("/delete/{eid}")
    @ApiOperation("删除活动")
    public CommonResult delete(@PathVariable("eid") Integer eid) {
        boolean delete = extensionService.delete(eid);
        return delete ? CommonResult.success("删除成功") : CommonResult.failed("删除失败");
    }

    @GetMapping("/getTimeAndId")
    @ApiOperation("获取事件和ID")
    public CommonResult<HashSet<Integer>> getTimeAndIds() throws ParseException {
        return CommonResult.success(extensionService.getIds());
    }
}
