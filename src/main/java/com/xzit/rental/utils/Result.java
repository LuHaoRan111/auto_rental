package com.xzit.rental.utils;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一返回结果
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;

    private Result(){}


    /**
     *
     * 增加，删除，修改操作成功的方法
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(){
        return new Result<T>()
                .setSuccess(true)
                .setCode(ResultCode.SUCCESS)
                .setMessage("操作成功");
    }


    /**
     *
     * 查询操作成功的方法
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T data){
        return new Result<T>()
                .setSuccess(true)
                .setCode(ResultCode.SUCCESS)
                .setMessage("查询成功")
                .setData(data);
    }

    /**
     * 失败的方法
     * @return
     * @param <T>
     */
    public static <T> Result<T> error(){
        return new Result<T>()
                .setSuccess(false)
                .setCode(ResultCode.ERROR)
                .setMessage("操作失败");
    }
}
