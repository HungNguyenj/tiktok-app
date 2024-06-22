package com.example.tiktokapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIRespone<T extends AbstractModel> {
    private Integer err;
    public String mes;
    @SerializedName(value = "posts", alternate = {"users"})
    public List<T> data;
    public Pagination pagination;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
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

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
