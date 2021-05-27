package com.chase.play.model;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/10/24 10:43
 */
public class Extension {

    private Integer id;
    private Integer uid;
    private Integer state;

    private String name;
    private Integer type;
    private Integer number;

    private String originator;
    private String startTime;
    private String location;

    public Integer getId() {
        return id;
    }

    public Integer getUid() {
        return uid;
    }

    public Integer getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public Integer getNumber() {
        return number;
    }

    public String getOriginator() {
        return originator;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getLocation() {
        return location;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Extension{" +
                "id=" + id +
                ", uid=" + uid +
                ", state=" + state +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", number=" + number +
                ", originator='" + originator + '\'' +
                ", startTime='" + startTime + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
