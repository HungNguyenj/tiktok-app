package com.example.tiktokapp.responseModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@RequiredArgsConstructor
public class APIResponeList<T> {
    private int err;
    private String mes;
    @SerializedName(value = "posts", alternate = {"users","followers","followings","comments"})
    private List<T> data;
    private Pagination pagination;

    public APIResponeList(int err, String mes, List<T> data, Pagination pagination) {
        this.err = err;
        this.mes = mes;
        this.data = data;
        this.pagination = pagination;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
