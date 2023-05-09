package com.example.scw.pojo.entity;

import com.example.scw.pojo.PojoCheck;
import com.example.scw.pojo.exception.ParameterException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Comment", description = "评价存储实体类")
public class Comment implements PojoCheck {
    @ApiModelProperty(value = "唯一id，等于从属团队任务id")
    private Integer belongTeamWork;
    @ApiModelProperty(value = "评价描述")
    private String description;
    @ApiModelProperty(value = "评价分数")
    private Integer score;
    @ApiModelProperty(value = "提交状态，0暂存，1提交")
    private Integer status;

    public Integer getBelongTeamWork() {
        return belongTeamWork;
    }

    public void setBelongTeamWork(Integer belongTeamWork) {
        this.belongTeamWork = belongTeamWork;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean checkValue() throws ParameterException {
        if (belongTeamWork == null) {
            return false;
        }
        return status != null;
    }
}
