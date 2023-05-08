package com.example.scw.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SingleWork", description = "个人任务存储实体类")
public class SingleWork {
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
    private Integer Status;

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
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}
