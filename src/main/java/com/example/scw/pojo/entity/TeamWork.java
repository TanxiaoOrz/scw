package com.example.scw.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "TeamWork", description = "小组任务存储实体类")
public class TeamWork {

    @ApiModelProperty(value = "唯一id")
    Integer teamWorkId;
    @ApiModelProperty(value = "任务描述")
    String workDescription;
    @ApiModelProperty(value = "成果提交的访问路径")
    String production;
    @ApiModelProperty(value = "从属队伍id")
    Integer belongTeam;
    @ApiModelProperty(value = "从属学习任务id")
    Integer belongWork;
    @ApiModelProperty(value = "任务状态，0是未提交，1是已提交")
    Integer status;

    public Integer getTeamWorkId() {
        return teamWorkId;
    }

    public void setTeamWorkId(Integer teamWorkId) {
        this.teamWorkId = teamWorkId;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public Integer getBelongTeam() {
        return belongTeam;
    }

    public void setBelongTeam(Integer belongTeam) {
        this.belongTeam = belongTeam;
    }

    public Integer getBelongWork() {
        return belongWork;
    }

    public void setBelongWork(Integer belongWork) {
        this.belongWork = belongWork;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
