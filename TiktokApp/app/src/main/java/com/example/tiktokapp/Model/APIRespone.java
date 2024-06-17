package com.example.tiktokapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIRespone<T extends AbstractModel> {
    private Integer err;
    public String mes;
    @SerializedName(value = "posts", alternate = {"users"})
    public List<T> data;
    public Pagination pagination;
}
