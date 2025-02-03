package com.umpisa.reservation.responseModel;

public class ApiResponse<T> {
    private Status status;
    private T data;
    private int totalCount;

    public ApiResponse(String message, T data, int statusCode, int totalCount) {
        this.status = new Status(statusCode, message);
        this.data = data;
        this.totalCount = totalCount;
    }

    public ApiResponse(String message, T data, int statusCode) {
        this.status = new Status(statusCode, message);
        this.data = data;
    }

    public ApiResponse(String message, int statusCode) {
        this.status = new Status(statusCode, message);
        this.data = null;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}