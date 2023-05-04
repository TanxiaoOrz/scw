package com.example.scw.pojo.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "TeamWork",description = "小组任务存储实体类")
public class TeamWork {

    Integer teamWorkId;
    String workDescription;
    String production;
    Integer belongTeam;
    Integer belongWork;
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
