package com.chase.play.model;

import java.math.BigDecimal;

/**
 * @version 1.0
 * @Description 用户信誉积分
 * @Author chase
 * @Date 2020/10/25 19:17
 */
public class UserCredit {

    private Integer uid;
    private BigDecimal credit;
    private Integer evalNum;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Integer getEvalNum() {
        return evalNum;
    }

    public void setEvalNum(Integer evalNum) {
        this.evalNum = evalNum;
    }

    @Override
    public String toString() {
        return "UserCredit{" +
                "uid=" + uid +
                ", credit=" + credit +
                ", evalNum=" + evalNum +
                '}';
    }
}
