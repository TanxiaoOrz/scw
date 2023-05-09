package com.example.scw.utils.factory;

import com.example.scw.mapper.WorkMapper;
import com.example.scw.pojo.dto.TeamWorkDto;
import com.example.scw.pojo.entity.Comment;
import com.example.scw.pojo.entity.TeamWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamWorkFactory implements Factory<TeamWorkDto, TeamWork>{

    @Autowired
    WorkMapper workMapper;

    @Override
    public TeamWorkDto getOne(TeamWork teamWork) {
        TeamWorkDto teamWorkDto = new TeamWorkDto(teamWork);
        teamWorkDto.setStudyWork(workMapper.getStudyWork(teamWork.getBelongWork()));
        teamWorkDto.setSingleWorks(workMapper.getSingleWorkByTeamWork(teamWork.getTeamWorkId()));
        Comment comment = workMapper.getComment(teamWork.getTeamWorkId());
        if (comment.getStatus()==1)
            teamWorkDto.setComment(comment);
        return teamWorkDto;
    }

    @Override
    public List<TeamWorkDto> getList(List<TeamWork> teamWorks) {
        List<TeamWorkDto> teamWorkDtos = new ArrayList<>(teamWorks.size());
        teamWorks.forEach(teamWork -> teamWorkDtos.add(getOne(teamWork)));
        return teamWorkDtos;
    }
}
