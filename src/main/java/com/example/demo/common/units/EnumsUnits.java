package com.example.demo.common.units;

import com.example.demo.common.enums.BaseEnum;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-05-09 16:33
 **/
public class EnumsUnits {

    public static <T extends BaseEnum> T getNameByCode(Integer code, Class<T> enumClasss) {
        for (T each : enumClasss.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}