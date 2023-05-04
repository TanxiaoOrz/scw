package com.example.scw.pojo.exception;

import com.example.scw.pojo.vo.Vo;

public class DataException extends ErrorException{

    public DataException(String description) {
        super(Vo.WRONG_DATA_STORE, description);
    }

    public DataException() {
        this(null);
    }
}
