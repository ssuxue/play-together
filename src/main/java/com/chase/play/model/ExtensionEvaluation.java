package com.chase.play.model;

import java.math.BigDecimal;

/**
 * @version 1.0
 * @Description 活动评价
 * @Author chase
 * @Date 2020/10/25 19:17
 */
public class ExtensionEvaluation {

    private Integer eid;
    private BigDecimal evaluation;
    private Integer evalNum;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public BigDecimal getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(BigDecimal evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getEvalNum() {
        return evalNum;
    }

    public void setEvalNum(Integer evalNum) {
        this.evalNum = evalNum;
    }

    @Override
    public String toString() {
        return "ExtensionEvaluation{" +
                "eid=" + eid +
                ", evaluation=" + evaluation +
                ", evalNum=" + evalNum +
                '}';
    }
}
