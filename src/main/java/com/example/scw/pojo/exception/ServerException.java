package com.example.scw.pojo.exception;

import com.example.scw.pojo.vo.Vo;

public class ServerException extends ErrorException {
    public ServerException(String description) {
        super(Vo.SERVER_ERROR,description);
    }

    public ServerException() {
        super(Vo.SERVER_ERROR);
    }
}
