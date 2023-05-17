package com.example.scw.pojo.dto;

import com.example.scw.pojo.entity.SingleWork;
import com.example.scw.pojo.entity.StudyWork;
import com.example.scw.pojo.entity.TeamWork;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SingleWorkDto", description = "个人业务运算类")
public class SingleWorkDto extends SingleWork {
    @ApiModelProperty("从属的学习任务")
    StudyWork studyWork;
    @ApiModelProperty("从属的团队任务")
    TeamWork teamWork;

    public SingleWorkDto(SingleWork singleWork) {
        setSingleWorkId(singleWork.getSingleWorkId());
        setWorkDescription(singleWork.getWorkDescription());
        setBelongWork(singleWork.getBelongWork());
        setBelongStudent(singleWork.getBelongStudent());
        setProductionRoute(singleWork.getProductionRoute());
        setStatus(singleWork.getStatus());
    }

    public StudyWork getStudyWork() {
        return studyWork;
    }

    public void setStudyWork(StudyWork studyWork) {
        this.studyWork = studyWork;
    }

    public TeamWork getTeamWork() {
        return teamWork;
    }

    public void setTeamWork(TeamWork teamWork) {
        this.teamWork = teamWork;
    }

}
