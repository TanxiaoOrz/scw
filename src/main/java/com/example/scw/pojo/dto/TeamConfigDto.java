package com.example.scw.pojo.dto;

import com.example.scw.pojo.PojoCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

@ApiModel(value = "TeamConfigDto",description = "全局团队设置运算类")
public class TeamConfigDto implements PojoCheck {

    @ApiModelProperty("队伍人数上线，修改设置时最大不要超过8")
    private Integer teamMemberMax;

    @ApiModelProperty("队长的显示字符")
    private String teamLeader;

    @ApiModelProperty("队伍成员1的显示字符")
    private String teamMember1;

    @ApiModelProperty("队伍成员1的显示字符")
    private String teamMember2;

    @ApiModelProperty("队伍成员1的显示字符")
    private String teamMember3;

    @ApiModelProperty("队伍成员1的显示字符")
    private String teamMember4;

    @ApiModelProperty("队伍成员1的显示字符")
    private String teamMember5;

    @ApiModelProperty("队伍成员1的显示字符")
    private String teamMember6;

    @ApiModelProperty("队伍成员1的显示字符")
    private String teamMember7;

    public Integer getTeamMemberMax() {
        return teamMemberMax;
    }

    public boolean checkValue() {
        if (teamMemberMax>8||teamMemberMax<=0)
            return false;
        switch (teamMemberMax) {
            case 8:
                if (!StringUtils.hasText(teamMember7))
                    return false;
            case 7:
                if (!StringUtils.hasText(teamMember6))
                    return false;
            case 6:
                if (!StringUtils.hasText(teamMember5))
                    return false;
            case 5:
                if (!StringUtils.hasText(teamMember4))
                    return false;
            case 4:
                if (!StringUtils.hasText(teamMember3))
                    return false;
            case 3:
                if (!StringUtils.hasText(teamMember2))
                    return false;
            case 2:
                if (!StringUtils.hasText(teamMember1))
                    return false;
            case 1:
                if (!StringUtils.hasText(teamLeader))
                    return false;
                break;
        }
        return true;
    }

    public void setTeamMemberMax(Integer teamMemberMax) {
        this.teamMemberMax = teamMemberMax;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getTeamMember1() {
        return teamMember1;
    }

    public void setTeamMember1(String teamMember1) {
        this.teamMember1 = teamMember1;
    }

    public String getTeamMember2() {
        return teamMember2;
    }

    public void setTeamMember2(String teamMember2) {
        this.teamMember2 = teamMember2;
    }

    public String getTeamMember3() {
        return teamMember3;
    }

    public void setTeamMember3(String teamMember3) {
        this.teamMember3 = teamMember3;
    }

    public String getTeamMember4() {
        return teamMember4;
    }

    public void setTeamMember4(String teamMember4) {
        this.teamMember4 = teamMember4;
    }

    public String getTeamMember5() {
        return teamMember5;
    }

    public void setTeamMember5(String teamMember5) {
        this.teamMember5 = teamMember5;
    }

    public String getTeamMember6() {
        return teamMember6;
    }

    public void setTeamMember6(String teamMember6) {
        this.teamMember6 = teamMember6;
    }

    public String getTeamMember7() {
        return teamMember7;
    }

    public void setTeamMember7(String teamMember7) {
        this.teamMember7 = teamMember7;
    }
}
