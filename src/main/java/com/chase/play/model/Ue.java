package com.chase.play.model;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/24 10:47
 */
public class Ue {

    private Integer uid;
    private Integer eid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getEid() {
        return eid;
    }

    @Override
    public String toString() {
        return "Ue{" +
                "uid=" + uid +
                ", eid=" + eid +
                '}';
    }
}
