package com.example.scw.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "标准包装类对返回的信息进行结果包装或错误描述")
public class Vo<Value> {
    // 正常返回
    public static final int SUCCESS = 0;
    // 没有权限
    public static final int NO_AUTHORITY = 1;
    // token过期
    public static final int TOKEN_OUT = 2;
    // 错误的数据携带
    public static final int WRONG_DATA_POST = 3;
    // 错误的数据存储
    public static final int WRONG_DATA_STORE = 4;
    // 运行异常
    public static final int SEVEVE_ERROR = 5;
    // 基本的描述信息存储
    public static final String[] DESCRIPTIONS = {
            "正常返回",
            "缺少权限",
            "登录过期",
            "错误数据携带",
            "错误数据存储",
            "服务器运行错误"
    };

    @ApiModelProperty(value = "返回成功与否描述\n" +
            "0:正常返回\n" +
            "1:缺少权限\n" +
            "2:登录过期" +
            "3:错误数据携带\n" +
            "4:错误数据存储\n" +
            "5:服务器运行错误")
    int status;// 对于返回
    @ApiModelProperty(value = "返回的具体数据,如异常返回会是异常的具体描述文字")
    Value data;
    @ApiModelProperty(value = "信息描述")
    String description;

    public Vo(int status, Value data, String description) {
        this.status = status;
        this.data = data;
        this.description = description;
    }

    public Vo(Value data) {
        this(Vo.SUCCESS, data, DESCRIPTIONS[Vo.SUCCESS]);
    }

    public Vo(int status, Value description) {
        this(status, description, DESCRIPTIONS[status]);
    }

    public Vo(int status) {
        this(status, null, DESCRIPTIONS[status]);
    }

    public int getStatus() {
        return status;
    }

    public Value getData() {
        return data;
    }

    public String getDescription() {
        return description;
    }
}