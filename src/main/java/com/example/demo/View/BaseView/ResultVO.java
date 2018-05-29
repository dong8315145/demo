package com.example.demo.View.BaseView;

import com.example.demo.common.enums.ResultEnum;
import lombok.Data;

@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 具体数据
     */
    private T data;

    public ResultVO() {
    }

    public ResultVO (ResultEnum resultEnum,T data) {
        switch (resultEnum) {
            case FAIL:
                this.code = ResultEnum.FAIL.getCode();
                this.msg = ResultEnum.FAIL.getMsg();
                this.data = data;
                break;
            case SUCCESS:
                this.code = ResultEnum.FAIL.getCode();
                this.msg = ResultEnum.FAIL.getMsg();
                this.data = data;
                break;
        }
    }
          public ResultVO (ResultEnum resultEnum) {
            switch (resultEnum){
                case FAIL:
                    this.code=ResultEnum.FAIL.getCode();
                    this.msg=ResultEnum.FAIL.getMsg();
                    this.data=null;
                    break;
                case SUCCESS:
                    this.code=ResultEnum.FAIL.getCode();
                    this.msg=ResultEnum.FAIL.getMsg();
                    this.data=null;
                    break;
            }
    }



}
