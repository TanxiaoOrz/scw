package com.example.scw.service;

import com.example.scw.pojo.dto.TeamConfigDto;
import com.example.scw.pojo.entity.Team;
import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.exception.ParameterException;
import com.example.scw.utils.TeamConfigUtils;

public interface TeamService {

    String updateTeamConfig(TeamConfigDto teamConfigDto) throws ParameterException;

    TeamConfigDto getTeamConfig();
    Team getTeam(User user) throws ParameterException;

}
