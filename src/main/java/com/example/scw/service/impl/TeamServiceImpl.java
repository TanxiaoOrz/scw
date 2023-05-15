package com.example.scw.service.impl;

import com.example.scw.mapper.TeamMapper;
import com.example.scw.pojo.dto.TeamConfigDto;
import com.example.scw.pojo.entity.Team;
import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.exception.ParameterException;
import com.example.scw.service.TeamService;
import com.example.scw.utils.TeamConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamConfigUtils teamConfigUtils;

    @Autowired
    TeamMapper teamMapper;

    @Override
    public String updateTeamConfig(TeamConfigDto teamConfigDto) throws ParameterException {
        if (!teamConfigDto.checkValue())
            throw new ParameterException("队伍数字设置异常或没有设置全部成员的职责名");
        teamConfigUtils.update(teamConfigDto);
        return "修改成功";
    }

    @Override
    public TeamConfigDto getTeamConfig() {
        return teamConfigUtils.getDto();
    }

    @Override
    public Team getTeam(User user) throws ParameterException {
        Team team = teamMapper.getTeam(user.getUserTeam());
        if(team == null){
            throw new ParameterException("该用户暂未加入团队");
        }
        return team;
    }
}
