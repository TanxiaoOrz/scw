package com.example.scw.pojo.exception;

import com.example.scw.pojo.vo.Vo;

public class ParameterException extends ErrorException{

    public ParameterException() {
        super(Vo.WRONG_DATA_POST);
    }

    public ParameterException(String description) {
        super(Vo.WRONG_DATA_POST,description);
    }

}
