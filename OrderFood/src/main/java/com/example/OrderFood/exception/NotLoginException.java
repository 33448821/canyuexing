package com.example.OrderFood.exception;

/**
 * Created by 永恒星辰
 * Date 2024/3/1 2:21
 * Description
 */
public class NotLoginException extends RuntimeException{
    public NotLoginException() {
        super("用户未登录");
    }
}