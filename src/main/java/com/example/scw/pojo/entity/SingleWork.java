package com.example.scw.pojo.entity;

import com.example.scw.pojo.PojoCheck;
import com.example.scw.pojo.ToCreateNotification;
import com.example.scw.pojo.exception.DataException;
import com.example.scw.pojo.exception.ParameterException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

@ApiModel(value = "SingleWork", description = "个人任务存储实体类")
public class SingleWork implements PojoCheck, ToCreateNotification {
    @ApiModelProperty(value = "唯一id")
    private Integer singleWorkId;
    @ApiModelProperty(value = "个人任务描述要求")
    private String workDescription;
    @ApiModelProperty(value = "从属学生id")
    private Integer belongStudent;
    @ApiModelProperty(value = "从属团队任务id")
    private Integer belongWork;
    @ApiModelProperty(value = "成果路径")
    private String productionRoute;
    @ApiModelProperty(value = "提交状态")
    private Integer status;

    public Integer getSingleWorkId() {
        return singleWorkId;
    }

    public void setSingleWorkId(Integer singleWorkId) {
        this.singleWorkId = singleWorkId;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public Integer getBelongStudent() {
        return belongStudent;
    }

    public void setBelongStudent(Integer belongStudent) {
        this.belongStudent = belongStudent;
    }

    public Integer getBelongWork() {
        return belongWork;
    }

    public void setBelongWork(Integer belongWork) {
        this.belongWork = belongWork;
    }

    public String getProductionRoute() {
        return productionRoute;
    }

    public void setProductionRoute(String productionRoute) {
        this.productionRoute = productionRoute;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean checkValue(){
        if (belongStudent == null) {
            return false;
        }
        if (belongWork == null) {
            return false;
        }
        if (status == null) {
            return false;
        }
        return StringUtils.hasText(workDescription);
    }

    @Override
    public String createNotificationString() throws DataException {
        String not ;
        if (status == 1) {
            not = String.format("你布置的%s已完成提交，请检查", workDescription);
        } else {
            throw new DataException("错误的个人任务状态码调用生成通知");
        }
        return not;
    }
}
