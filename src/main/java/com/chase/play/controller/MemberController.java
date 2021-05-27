package com.chase.play.controller;

import com.chase.play.common.api.CommonResult;
import com.chase.play.model.User;
import com.chase.play.service.AdminService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Description 后台用户管理
 * @Author chase
 * @Date 2020/9/21 23:19
 */
@Controller
@Api(tags = "后台用户管理")
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult modifyPassword(@RequestParam("email") @ApiParam("邮箱") String email,
                                       @RequestParam("pwd") @ApiParam("密码") String password,
                                       @RequestParam("code2") @ApiParam("验证码") String captcha) {
        // 验证码验证
        boolean result = adminService.verifyCaptcha(email, captcha);
        if (!result) {
            return CommonResult.failed("验证码错误！");
        }
        User user = new User();
        user.setEmail(email);
        user.setPwd(passwordEncoder.encode(password));

        boolean update = adminService.updateUserInfo(user);
        if (update) {
            return CommonResult.success("success");
        }
        return CommonResult.failed("修改失败！");
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/modifyInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult modifyUserInfo(@ApiParam("用户信息") @RequestParam("user") String userString) {
        Gson gson = new Gson();
        User user = gson.fromJson(userString, User.class);

        // 前台修改信息都把密码传过来，明明有修改密码的接口，唉呀不敢BB只能手动在后台悄咪咪地改
        if (user.getPwd() != null || "".equals(user.getPwd())) {
            user.setPwd(passwordEncoder.encode(user.getPwd()));
        }

        boolean update = adminService.updateUserInfo(user);
        if (update) {
            return CommonResult.success("success");
        }
        return CommonResult.failed("修改失败！");
    }

    @ApiOperation(value = "学生认证")
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult authentication(@RequestParam("user") String userString) {
        Gson gson = new Gson();
        User user = gson.fromJson(userString, User.class);

        boolean result = adminService.validateSid(user.getSid());
        if  (result) {
            return CommonResult.failed("你已认证过！");
        }

        User student = adminService.getUser(user.getEmail());
        if (student.getState() ==  1) {
            return CommonResult.failed("你已认证过！");
        }

        if (adminService.updateUserInfo(user)) {
            return CommonResult.success("success");
        }
        return CommonResult.failed("认证失败！");
    }
}
