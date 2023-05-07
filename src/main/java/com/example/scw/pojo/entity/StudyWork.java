package com.example.scw.pojo.entity;

import com.example.scw.pojo.PojoCheck;
import com.example.scw.pojo.ToCreateNotification;
import com.example.scw.pojo.exception.DataException;
import com.example.scw.pojo.exception.ParameterException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.util.Date;

@ApiModel(value = "StudyWork",description = "教师创建的学习任务实体类")
public class StudyWork implements PojoCheck, ToCreateNotification {
    @ApiModelProperty("唯一id")
    private Integer workId;
    @ApiModelProperty("任务负责人,0代表组长，1-7代表团队成员1-7")
    private Integer publisher;
    @ApiModelProperty("描述内容")
    private String content;
    @ApiModelProperty("开始时间")
    private Date releaseTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("附带资源路径")
    private String resourceRoute;
    @ApiModelProperty("该学习状态,0代表待发布，1代表已发布未截至，2代表已截至")
    private Integer status;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getPublisher() {
        return publisher;
    }

    public void setPublisher(Integer publisher) {
        this.publisher = publisher;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getResourceRoute() {
        return resourceRoute;
    }

    public void setResourceRoute(String resourceRoute) {
        this.resourceRoute = resourceRoute;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean checkValue() throws ParameterException {
        if (publisher == null) {
            return false;
        }
        if (content == null) {
            return false;
        }
        if (releaseTime == null) {
            return false;
        }
        if (endTime == null) {
            return false;
        }else if (releaseTime.after(endTime))
            throw new ParameterException("结束时间不能在发布时间之前");
        if (status == null) {
            return false;
        }else {
            Date now = new Date();
            if (endTime.before(now))
                status = 2;
            else if (releaseTime.before(now))
                    status = 1;
                else
                    status = 0;
        }
        if (!StringUtils.hasText(content))
            return false;
        return StringUtils.hasText(resourceRoute);
    }

    @Override
    public String createNotificationString() throws DataException {
        String not = null;
        switch (status) {
            case 1:
                not = String.format("%s,新的学习任务:%20s...,截至时间是%s",releaseTime.toString(),content,endTime.toString());
                break;
            case 2:
                not = String.format("%s,学习任务:%20s...,已截止如有未提交请单独联系老师",endTime.toString(),content);
                break;
            default:
                throw new DataException("错误的学习任务状态码调用生成通知");
        }
        return not;
    }
}
