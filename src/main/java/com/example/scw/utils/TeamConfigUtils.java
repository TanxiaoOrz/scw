package com.example.scw.utils;

import com.example.scw.mapper.TeamMapper;
import com.example.scw.pojo.dto.TeamConfigDto;
import com.example.scw.pojo.entity.StudyWork;
import com.example.scw.pojo.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TeamConfigUtils {

    @Value("${team.count}")
    private Integer teamMemberMax;

    @Value("${team.memberName[0]}")
    private String teamLeader;

    @Value("${team.memberName[1]}")
    private String teamMember1;

    @Value("${team.memberName[2]}")
    private String teamMember2;

    @Value("${team.memberName[3]}")
    private String teamMember3;

    @Value("${team.memberName[4]}")
    private String teamMember4;

    @Value("${team.memberName[5]}")
    private String teamMember5;

    @Value("${team.memberName[6]}")
    private String teamMember6;

    @Value("${team.memberName[7]}")
    private String teamMember7;

    private final String[] teamMemberStored = {
            "TeamLeader",
            "TeamMember1",
            "TeamMember2",
            "TeamMember3",
            "TeamMember4",
            "TeamMember5",
            "TeamMember6",
            "TeamMember7",

    };

    @Autowired
    TeamMapper teamMapper;

    public Integer getTeamMemberMax() {
        return teamMemberMax;
    }

    public void setTeamMemberMax(Integer teamMemberMax) {
        this.teamMemberMax = teamMemberMax;
    }

    public TeamConfigDto getDto() {
        TeamConfigDto teamConfigDto = new TeamConfigDto();
        teamConfigDto.setTeamLeader(teamLeader);
        teamConfigDto.setTeamMember1(teamMember1);
        teamConfigDto.setTeamMember2(teamMember2);
        teamConfigDto.setTeamMember3(teamMember3);
        teamConfigDto.setTeamMember4(teamMember4);
        teamConfigDto.setTeamMember5(teamMember5);
        teamConfigDto.setTeamMember6(teamMember6);
        teamConfigDto.setTeamMember7(teamMember7);
        teamConfigDto.setTeamMemberMax(teamMemberMax);
        return teamConfigDto;
    }

    public void update(TeamConfigDto teamConfigDto) {
        teamMemberMax = teamConfigDto.getTeamMemberMax();
        switch (teamMemberMax) {
            case 8:
                teamMember7 = teamConfigDto.getTeamMember7();
            case 7:
                teamMember6 = teamConfigDto.getTeamMember6();
            case 6:
                teamMember5 = teamConfigDto.getTeamMember5();
            case 5:
                teamMember4 = teamConfigDto.getTeamMember4();
            case 4:
                teamMember3 = teamConfigDto.getTeamMember3();
            case 3:
                teamMember2 = teamConfigDto.getTeamMember2();
            case 2:
                teamMember1 = teamConfigDto.getTeamMember1();
            case 1:
                teamLeader = teamConfigDto.getTeamLeader();
                break;
        }
    }

    public Integer getPublisherId(Team userTeam, StudyWork studyWork) {
        return teamMapper.getPublisherId(teamMemberStored[studyWork.getPublisher()],userTeam.getTeamId());
    }

}
