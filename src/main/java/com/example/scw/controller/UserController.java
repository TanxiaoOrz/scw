package com.example.scw.controller;

import com.example.scw.pojo.entity.Team;
import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.exception.ParameterException;
import com.example.scw.pojo.vo.Vo;
import com.example.scw.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.scw.pojo.vo.UserLogin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "传入登录结构体,返回登录状态验证的token字符串")
    @ApiImplicitParam(name = "user",value = "登录用户结构体",required = true,dataTypeClass = UserLogin.class,paramType = "body")
    public Vo<String> userLogin(@RequestBody UserLogin user) throws ParameterException {
        return new Vo<>(userService.login(user));
    }

    @GetMapping("/information")
    @ApiOperation(value = "用户获取基本信息接口", notes = "检查登录字符串")
    public Vo<User> getInformation(HttpServletRequest request) {
        User user = (User) request.getAttribute("User");
        return new Vo<>(user);
    }

}
