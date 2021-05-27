package com.chase.play.controller;

import com.chase.play.common.api.CommonResult;
import com.chase.play.common.api.EmailPurposeEnum;
import com.chase.play.model.User;
import com.chase.play.service.AdminService;
import com.chase.play.service.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Description 系统管理Controller
 * @Author chase
 * @Date 2020/9/23 22:27
 */
@Controller
@Api(tags = "后台系统管理")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<User> login(@Validated @RequestParam("email") String email, @RequestParam("pwd") String password) {
        Assert.notNull(email, "邮箱不能为空");
        Assert.notNull(password, "密码不能为空");

        User student = adminService.getUser(email);
        if(!passwordEncoder.matches(password, student.getPwd())){
            return CommonResult.failed("邮箱不存在或密码错误");
        }

        User user = adminService.login(email, password);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(user, "success");
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult registry(@ApiParam("注册的用户信息") @RequestParam("user") String userString,
                                 @ApiParam("验证码") @RequestParam("captcha") String captcha) throws JsonProcessingException {
        Assert.notNull(captcha, "验证码不能为空！");

        Gson gson = new Gson();
        User user = gson.fromJson(userString, User.class);

        user.setUserName("未命名用户");

        boolean result = adminService.verifyCaptcha(user.getEmail(), captcha);
        if (!result) {
            return CommonResult.failed("验证码错误！");
        }

        if (adminService.getUser(user.getEmail()) != null) {
            return CommonResult.failed("该邮箱已注册过！");
        }

        user.setPwd(passwordEncoder.encode(user.getPwd()));
        if (!adminService.registry(user)) {
            return CommonResult.failed("注册失败！");
        }
        return CommonResult.success("success");
    }

    @ApiOperation("获取验证码")
    @PostMapping("/getCaptcha")
    @ResponseBody
    public CommonResult getCaptcha(@RequestParam("type") @ApiParam("请求用途") String type,
                                   @RequestParam("email") @ApiParam("邮箱") String email) {
        String captcha = null;
        String content = null;

        // 进行密码找回邮件发送
        if (type.equals(EmailPurposeEnum.RETRIEVE.getParam())) {
            captcha = adminService.generateCaptcha(email);
            content = "<strong style='display:block;margin-bottom:15px;'>" + " 您正在找回密码，请在验证码输入框中输入：" +
                              "<span style='color:#f60;font-size: 24px'>" + captcha +
                              "</span>以完成操作。</strong>";
            mailService.sendHtmlMail(email,  "一起玩APP找回密码验证", content);
            return CommonResult.success("success");
        }

        // 进行注册邮件发送
        if (type.equals(EmailPurposeEnum.REGISTER.getParam())) {
            captcha = adminService.generateCaptcha(email);
            content = "<strong style='display:block;margin-bottom:15px;'>" + " 您的验证码为：" +
                    "<span style='color:#f60;font-size: 24px'>" + captcha +
                    "</span><br>欢迎注册一起玩APP，请将验证码填写到注册页面</strong>";
            mailService.sendHtmlMail(email,  "注册一起玩APP邮箱验证", content);
            return CommonResult.success("success");
        }

        // 进行密码修改邮件发送
        captcha = adminService.generateCaptcha(email);
        content = "<strong style='display:block;margin-bottom:15px;'>" + " 您正在修改密码，请在验证码输入框中输入：" +
                "<span style='color:#f60;font-size: 24px'>" + captcha +
                "</span>以完成操作。</strong>";
        mailService.sendHtmlMail(email,  "一起玩APP修改密码验证", content);
        return CommonResult.success("success");
    }
}
