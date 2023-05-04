package com.example.scw.utils.factory;

import com.example.scw.mapper.WorkMapper;
import com.example.scw.pojo.dto.TeamWorkDto;
import com.example.scw.pojo.entity.TeamWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamWorkFactory implements Factory<TeamWorkDto, TeamWork>{

    @Autowired
    WorkMapper workMapper;

    @Override
    public TeamWorkDto getOne(TeamWork teamWork) {
        return null;
    }

    @Override
    public List<TeamWorkDto> getList(List<TeamWork> teamWorks) {
        return null;
    }
}
