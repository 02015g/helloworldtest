package com.g01502.exam.exception;

public class BusinessException extends RuntimeException{
    private int code;
    BusinessException(int code,String message){
        super(message);
        this.code = code;
    }
    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
    public BusinessException(ErrorCode errorCode,String message){
        super(message);
        this.code = errorCode.getCode();
    }
    public int getCode() {
        return code;
    }
}
