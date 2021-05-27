package com.chase.play.service.impl;

import com.chase.play.dao.UserCreditMapper;
import com.chase.play.model.UserCredit;
import com.chase.play.service.UserCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/25 20:21
 */
@Service
public class UserCreditServiceImpl implements UserCreditService {

    @Autowired
    private UserCreditMapper userCreditMapper;

    @Override
    public UserCredit getOne(Integer uid) {
        return userCreditMapper.select(uid);
    }

    @Override
    public boolean calculate(UserCredit userCredit) {
        UserCredit user = userCreditMapper.select(userCredit.getUid());

        DecimalFormat df=new DecimalFormat("0.0");
        int evalNum = user.getEvalNum();
        int value = user.getCredit().intValue() * evalNum + userCredit.getCredit().intValue();
        String scores = df.format((float) value / (evalNum + 1));

        BigDecimal credit = new BigDecimal(scores);
        user.setCredit(credit);

        int update = userCreditMapper.update(user);
        return update > 0;
    }
}
