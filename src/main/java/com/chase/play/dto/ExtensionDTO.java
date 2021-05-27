package com.chase.play.dto;

/**
 * @version 1.0
 * @Description
 * @Author chase
 * @Date 2020/11/3 23:23
 */
public class ExtensionDTO {

    private Integer id;
    private String time;

    public ExtensionDTO() {
    }

    public ExtensionDTO(Integer id, String time) {
        this.id = id;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
