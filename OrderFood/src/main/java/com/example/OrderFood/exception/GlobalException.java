package com.example.OrderFood.exception;

import com.example.OrderFood.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by 永恒星辰
 * Date 2024/3/1 2:20
 * Description
 */
// 当throw new UsernameDuplicateException()时，会被这个类捕获到，接着处理
@RestControllerAdvice  // 返回Json格式数据
public class GlobalException{

    @ExceptionHandler(NotLoginException.class) // 指定捕获的异常
    public Result<String> notLoginException(Exception e){
        return Result.failure(408,e.getMessage());
    }

}