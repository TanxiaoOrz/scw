package com.example.scw.pojo.dto;

import com.example.scw.pojo.entity.Comment;
import com.example.scw.pojo.entity.SingleWork;
import com.example.scw.pojo.entity.TeamWork;

import java.util.List;

public class TeamWorkDto extends TeamWork {

    private Comment comment;
    private List<SingleWork> singleWorks;

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
}
