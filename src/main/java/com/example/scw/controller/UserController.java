package com.example.scw.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scw.pojo.vo.UserLogin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api("用户接口")
public class UserController {

    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "传入登录结构体,返回登录状态验证的token字符串")
    @ApiImplicitParam()
    public Vo<String> userLogin(@RequestBody UserLogin user) {

    }

}
