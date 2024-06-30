package com.example.tiktokapp.responseModel;

import com.google.gson.annotations.SerializedName;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@RequiredArgsConstructor
public class APIRespone<T> {
    private Integer err;
    private String mes;
    @SerializedName(value = "post", alternate = {"user","follower","following","comment"})
    private T data;
    private String accessToken;
    private Pagination pagination;

    public APIRespone(Integer err, String mes, T data, String accessToken, Pagination pagination) {
        this.err = err;
        this.mes = mes;
        this.data = data;
        this.accessToken = accessToken;
        this.pagination = pagination;
    }

    public Integer getErr() {
        return err;
    }

    public void setErr(Integer err) {
        this.err = err;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
