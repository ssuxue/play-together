package com.chase.play.dto;

/**
 * @version 1.0
 * @Description 文件上传返回结果
 * @Author chase
 * @Date 2020/9/21 20:35
 */
public class MinioUploadDTO {
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MinioUploadDTO{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
