package com.chase.play.service.impl;

import com.chase.play.dao.UserMapper;
import com.chase.play.model.User;
import com.chase.play.service.AdminService;
import com.chase.play.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Random;

/**
 * @version 1.0
 * @Description 后台用户管理实现类
 * @Author chase
 * @Date 2020/9/22 22:21
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.captcha}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.captcha}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public User login(String email, String password) {
        return userMapper.getLoginParam(email, password);
    }

    @Override
    public boolean registry(User user) {

        int result = userMapper.add(user);
        userMapper.addUserCredit(user.getUid());
        return result > 0;
    }

    @Override
    public String generateCaptcha(String email) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(6));
        }
        // 验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + email, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + email, AUTH_CODE_EXPIRE_SECONDS);
        return sb.toString();
    }

    @Override
    public boolean verifyCaptcha(String email, String captcha) {
        Assert.notNull(captcha, "验证码不能为空！");
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + email);
        if (captcha.equals(realAuthCode)) {
            return true;
        } else  {
            return false;
        }
    }

    @Override
    public User getUser(String email) {
        return userMapper.select(email);
    }

    @Override
    public boolean updateUserInfo(User user) {
        return userMapper.modify(user) > 0;
    }

    @Override
    public boolean validateSid(String sid) {
        return (userMapper.selectBySid(sid) != null);
    }

}
