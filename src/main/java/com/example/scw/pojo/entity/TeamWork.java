package com.example.scw.pojo.entity;

import com.example.scw.pojo.PojoCheck;
import com.example.scw.pojo.ToCreateNotification;
import com.example.scw.pojo.exception.DataException;
import com.example.scw.pojo.exception.ParameterException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "TeamWork", description = "小组任务存储实体类")
public class TeamWork implements PojoCheck, ToCreateNotification {

    @ApiModelProperty(value = "唯一id")
    Integer teamWorkId;
    @ApiModelProperty(value = "任务描述")
    String workDescription;
    @ApiModelProperty(value = "成果提交的访问路径")
    String productionRoute;
    @ApiModelProperty(value = "从属队伍id")
    Integer belongTeam;
    @ApiModelProperty(value = "从属学习任务id")
    Integer belongWork;
    @ApiModelProperty(value = "任务状态，0是未提交，1是已提交")
    Integer status;

    public Integer getTeamWorkId() {
        return teamWorkId;
    }

    public void setTeamWorkId(Integer teamWorkId) {
        this.teamWorkId = teamWorkId;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getProductionRoute() {
        return productionRoute;
    }

    public void setProductionRoute(String productionRoute) {
        this.productionRoute = productionRoute;
    }

    public Integer getBelongTeam() {
        return belongTeam;
    }

    public void setBelongTeam(Integer belongTeam) {
        this.belongTeam = belongTeam;
    }

    public Integer getBelongWork() {
        return belongWork;
    }

    public void setBelongWork(Integer belongWork) {
        this.belongWork = belongWork;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean checkValue() {
        if (teamWorkId == null) {
            return false;
        }
        if (belongTeam == null)
            return false;
        if (belongWork == null) {
            return false;
        }
        return status != null;
    }

    @Override
    public String createNotificationString() throws DataException {
        if (status == 1)
            return String.format("团队%d的学习任务%d已完成提交请批阅，成果下载路径%s",belongTeam,belongWork,productionRoute);
        else
            throw new DataException("错误的团队任务状态码调用生成通知");
    }
}
