package com.example.demo.View.BaseView;

import com.example.demo.common.enums.ResultEnum;

public class ResultVO {

    public static BaseVO success(Object object) {
        BaseVO baseVO = new BaseVO();
        baseVO.setCode(ResultEnum.SUCCESS.getCode());
        baseVO.setMsg(ResultEnum.SUCCESS.getMsg());
        baseVO.setData(object);
        return baseVO;
    }

    public static BaseVO success() {
        return success(null);
    }

    public static BaseVO fail(Integer code, String msg) {
        BaseVO baseVO = new BaseVO();
        baseVO.setCode(code);
        baseVO.setMsg(msg);
        return baseVO;
    }

    public static BaseVO fail() {
        BaseVO baseVO = new BaseVO();
        baseVO.setCode(ResultEnum.FAIL.getCode());
        baseVO.setMsg(ResultEnum.FAIL.getMsg());
        return baseVO;
    }

}
