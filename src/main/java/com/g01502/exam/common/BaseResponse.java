package com.g01502.exam.common;

import com.g01502.exam.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;
@Data
public class BaseResponse<T> implements Serializable {
    private T data;
    private String message;
    private int code;
    public BaseResponse(int code,String message,T data){
        this.data = data;
        this.message = message;
        this.code = code;
    }
    public BaseResponse(int code,T data){
        this(code,"",data);
    }
    public BaseResponse(ErrorCode errorCode){
        this(errorCode.getCode(),errorCode.getMessage(),null);
    }
}