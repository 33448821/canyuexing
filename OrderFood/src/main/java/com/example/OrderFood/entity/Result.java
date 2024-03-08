package com.example.OrderFood.entity;

/**
 * Created by 永恒星辰
 * Date 2024/2/29 2:10
 * Description
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // Getters and setters

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    // Static factory methods

    public static <T> Result<T> success() {
        return new Result<>(200, "请求成功", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "请求成功", data);
    }
    public static <T>Result<T> success(String msg){
        return new Result<>(200,msg,null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    public static <T> Result<T> failure() {
        return new Result<>(401, "请求失败", null);
    }

    public static <T> Result<T> failure(String msg) {
        return new Result<>(401, msg, null);
    }

    public static <T> Result<T> failure(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    // Override toString method

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
