package com.example.scw.pojo.exception;

import com.example.scw.pojo.vo.Vo;

public class AuthorityException extends ErrorException{

    public AuthorityException() {
        super(Vo.NO_AUTHORITY);
    }

    public AuthorityException(String description) {
        super(Vo.NO_AUTHORITY,description);
    }
}
