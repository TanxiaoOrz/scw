package com.example.scw.pojo.dto;

import com.example.scw.pojo.entity.StudyWork;
import com.example.scw.pojo.entity.TeamWork;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "StudyWorkDto",description = "学习任务完整运算构造体")
public class StudyWorkDto extends StudyWork {

    @ApiModelProperty("该学习任务的团队任务情况")
    private List<TeamWork> teamWorks;

    public StudyWorkDto(StudyWork studyWork) {
        setWorkId(studyWork.getWorkId());
        setContent(studyWork.getContent());
        setPublisher(studyWork.getPublisher());
        setReleaseTime(studyWork.getReleaseTime());
        setEndTime(studyWork.getEndTime());
        setResourceRoute(studyWork.getResourceRoute());
        setStatus(studyWork.getStatus());
    }

    public List<TeamWork> getTeamWorks() {
        return teamWorks;
    }

    public void setTeamWorks(List<TeamWork> teamWorks) {
        this.teamWorks = teamWorks;
    }
}
