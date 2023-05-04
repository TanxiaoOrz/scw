package com.example.scw.pojo.entity;

public class Comment {
    private Integer belongTeamWork;
    private String description;
    private Integer score;
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
}
