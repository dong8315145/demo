package com.example.demo.common.exception;

import com.example.demo.common.enums.ExceptionEnum;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-04-27 11:01
 **/
public class BaseException extends RuntimeException {

    private Integer code;

    public BaseException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

}
