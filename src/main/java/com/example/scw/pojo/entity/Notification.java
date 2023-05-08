package com.example.scw.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Notification", description = "通知存储实体类")
public class Notification {

    @ApiModelProperty("唯一id")
    private Integer notId;
    @ApiModelProperty(value = "发布人")
    private Integer publisher;
    @ApiModelProperty(value = "内容")
    private String content;

    public Integer getNotId() {
        return notId;
    }

    public void setNotId(Integer notId) {
        this.notId = notId;
    }

    public Integer getPublisher() {
        return publisher;
    }

    public void setPublisher(Integer publisher) {
        this.publisher = publisher;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
