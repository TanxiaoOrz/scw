package com.example.scw.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户存储实体")
public class User {

    @ApiModelProperty("用户唯一id")
    private Integer userId;
    @ApiModelProperty("学号或教师编号")
    private String userCode;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户密码")
    private String userPass;
    @ApiModelProperty("用户类型")
    private String userStatus;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

}
