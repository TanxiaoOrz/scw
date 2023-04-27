package com.example.scw.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserLogin",description = "登录结构体")
public class UserLogin {

    @ApiModelProperty("学号或教师编号")
    private String userCode;
    @ApiModelProperty("用户密码")
    private String userPass;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

}
