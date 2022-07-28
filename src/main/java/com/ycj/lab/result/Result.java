package com.ycj.lab.result;

import lombok.Builder;

import java.util.Date;

@Builder
public class Result {
    public int code;

    public Object data;

    public Date timestamp;

    public String msg;

    public static Result error(){
        return Result.builder().code(ResultStatus.FAIL).data(false).timestamp(new Date()).build();
    }

    public static Result error(Object data){
        return Result.builder().code(ResultStatus.FAIL).data(data).timestamp(new Date()).build();
    }

    public static Result success(){
        return Result.builder().code(ResultStatus.SUCCESS).data(true).timestamp(new Date()).build();
    }

    public static Result success(Object data){
        return Result.builder().code(ResultStatus.SUCCESS).data(data).timestamp(new Date()).build();
    }
}
