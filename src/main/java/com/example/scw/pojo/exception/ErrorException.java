package com.example.scw.pojo.exception;

public class ErrorException extends Exception {
    private int status;
    private String description;

    public ErrorException(int status, String description) {
        super();
        this.status = status;
        this.description = description;
    }

    public ErrorException(int status) {
        this(status, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
