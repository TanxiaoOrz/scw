package com.example.scw.pojo.entity;

public class SingleWork {
    private Integer singleWorkId;
    private String workDescription;
    private Integer belongStudent;
    private Integer belongWork;
    private String productionRoute;
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
