package com.example.scw.controller;

import com.example.scw.pojo.dto.TeamConfigDto;
import com.example.scw.pojo.dto.TeamWorkDto;
import com.example.scw.pojo.entity.Team;
import com.example.scw.pojo.exception.ParameterException;
import com.example.scw.pojo.vo.Vo;
import com.example.scw.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/team")
@Api(tags = "队伍接口")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/configGet")
    @ApiOperation(value = "获取队伍设置接口", notes = "仅检查登录字符串")
    public Vo<TeamConfigDto> getConfig() {
        return new Vo<>(teamService.getTeamConfig());
    }

    @PostMapping("/configUpdate")
    @ApiOperation(value = "修改队伍设置接口", notes = "验证教师登录\n会检查包含在数字内的成员职责名是否填写\n会检查成员数字是否超过范围")
    public Vo<String> modifyConfig(@RequestBody TeamConfigDto teamConfigDto) throws ParameterException {
        return new Vo<>(teamService.updateTeamConfig(teamConfigDto));
    }

    @GetMapping("/student")
    @ApiOperation(value = "获取所在团队信息")
    public Vo<Team> getTeam(HttpServletRequest request) {
        return null;
    }

}
