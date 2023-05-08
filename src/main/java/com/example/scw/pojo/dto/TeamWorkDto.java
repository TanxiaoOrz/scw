package com.example.scw.pojo.dto;

import com.example.scw.pojo.entity.Comment;
import com.example.scw.pojo.entity.SingleWork;
import com.example.scw.pojo.entity.StudyWork;
import com.example.scw.pojo.entity.TeamWork;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "TeamWorkDto", description = "团队任务运算类")
public class TeamWorkDto extends TeamWork {

    @ApiModelProperty("教师评价，当已提交且教师评价提交后才会出现")
    private Comment comment;
    @ApiModelProperty("生成的个人任务数组")
    private List<SingleWork> singleWorks;
    @ApiModelProperty("从属的学习任务")
    private StudyWork studyWork;

    public TeamWorkDto(TeamWork teamWork) {
        setTeamWorkId(teamWork.getTeamWorkId());
        setWorkDescription(teamWork.getWorkDescription());
        setProduction(teamWork.getProduction());
        setBelongTeam(teamWork.getBelongTeam());
        setBelongWork(teamWork.getBelongWork());
        setStatus(teamWork.getStatus());
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<SingleWork> getSingleWorks() {
        return singleWorks;
    }

    public void setSingleWorks(List<SingleWork> singleWorks) {
        this.singleWorks = singleWorks;
    }

    public StudyWork getStudyWork() {
        return studyWork;
    }

    public void setStudyWork(StudyWork studyWork) {
        this.studyWork = studyWork;
    }

}
