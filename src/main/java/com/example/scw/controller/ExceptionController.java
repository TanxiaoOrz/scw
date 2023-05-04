package com.example.scw.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.scw.pojo.exception.ErrorException;
import com.example.scw.pojo.vo.Vo;

@RestControllerAdvice
@Api(tags = "异常处理控制器")
@CrossOrigin
public class ExceptionController {

    @ExceptionHandler(ErrorException.class)
    public Vo<String> handleNoDataException(ErrorException e) {
        return new Vo<>(e.getStatus(), e.getDescription(), Vo.DESCRIPTIONS[e.getStatus()]);
    }

    @ExceptionHandler(Exception.class)
    public Vo<String> handleException(Exception e) {
        e.printStackTrace();
        return new Vo<>(Vo.SERVER_ERROR, e.getMessage());
    }
}