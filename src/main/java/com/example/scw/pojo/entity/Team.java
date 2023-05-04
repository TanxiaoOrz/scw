package com.example.scw.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Team",description = "队伍实体")
public class Team {

    @ApiModelProperty("队伍编号")
    private Integer teamId;
    @ApiModelProperty("队伍状态")
    private Integer teamStatus;
    @ApiModelProperty("队伍成员1")
    private Integer teamMember1;
    @ApiModelProperty("队伍成员2")
    private Integer teamMember2;
    @ApiModelProperty("队伍成员3")
    private Integer teamMember3;
    @ApiModelProperty("队伍成员4")
    private Integer teamMember4;
    @ApiModelProperty("队伍成员5")
    private Integer teamMember5;
    @ApiModelProperty("队伍成员6")
    private Integer teamMember6;
    @ApiModelProperty("队伍成员7")
    private Integer teamMember7;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(Integer teamStatus) {
        this.teamStatus = teamStatus;
    }

    public Integer getTeamMember1() {
        return teamMember1;
    }

    public void setTeamMember1(Integer teamMember1) {
        this.teamMember1 = teamMember1;
    }

    public Integer getTeamMember2() {
        return teamMember2;
    }

    public void setTeamMember2(Integer teamMember2) {
        this.teamMember2 = teamMember2;
    }

    public Integer getTeamMember3() {
        return teamMember3;
    }

    public void setTeamMember3(Integer teamMember3) {
        this.teamMember3 = teamMember3;
    }

    public Integer getTeamMember4() {
        return teamMember4;
    }

    public void setTeamMember4(Integer teamMember4) {
        this.teamMember4 = teamMember4;
    }

    public Integer getTeamMember5() {
        return teamMember5;
    }

    public void setTeamMember5(Integer teamMember5) {
        this.teamMember5 = teamMember5;
    }

    public Integer getTeamMember6() {
        return teamMember6;
    }

    public void setTeamMember6(Integer teamMember6) {
        this.teamMember6 = teamMember6;
    }

    public Integer getTeamMember7() {
        return teamMember7;
    }

    public void setTeamMember7(Integer teamMember7) {
        this.teamMember7 = teamMember7;
    }
}
