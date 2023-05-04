package com.example.scw.pojo;

import com.example.scw.pojo.exception.ParameterException;

public interface PojoCheck {
    /**
     * 检查当前实体值是否合法,通常用于是否所有属性有合法值，不包含主键不主动抛出异常
     * @return 检查结果
     */
    boolean checkValue() throws ParameterException;
}
