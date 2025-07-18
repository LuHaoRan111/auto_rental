package com.xzit.rental.config;


import com.xzit.rental.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public Result HandleException(Exception e){
        log.error("异常信息：{}",e.getMessage());
        e.printStackTrace();
        return Result.error().setMessage(e.getMessage());
    }
}
