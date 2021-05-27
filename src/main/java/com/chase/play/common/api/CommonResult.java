package com.chase.play.common.api;

/**
 * 通用返回对象
 *
 * @author chase
 * @date 2020/7/27
 */
public class CommonResult<T> {

    private String msg;
    private T data;

    protected CommonResult() {
    }

    /** 前台老哥习惯写msg不写message 这导致里编码不规范没办法了 **/
    protected CommonResult(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>("操作成功", data);
    }

    /**
     * 成功返回结果
     *
     * @param msg 提示信息
     */
    public static <T> CommonResult<T> success(String msg) {
        return new CommonResult<T>(msg, null);
    }

    /**
     *@Description 成功返回结果
     * @param data 获取的数据
     * @param  msg 提示信息
     */
    public static <T> CommonResult<T> success(T data, String msg) {
        return new CommonResult<T>(msg, data);
    }

    /**
     * 失败返回结果
     * @param msg 提示信息
     */
    public static <T> CommonResult<T> failed(String msg) {
        return new CommonResult<T>(msg, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed("操作失败");
    }

    /**
     * 参数验证失败返回结果
     * @param msg 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String msg) {
        return new CommonResult<T>(msg, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>("暂未登录或token已经过期", data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>("没有相关权限", data);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}