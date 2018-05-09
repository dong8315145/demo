package com.example.demo.common.exception;

import com.example.demo.common.enums.ResultEnum;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-04-27 11:01
 **/
public class ResultException extends RuntimeException {

    private Integer code;

    public ResultException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

}
