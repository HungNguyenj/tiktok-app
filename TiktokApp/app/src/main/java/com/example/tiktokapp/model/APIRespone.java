package com.example.tiktokapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class APIRespone<T extends AbstractModel> {
    private Integer err;
    private String mes;
    @SerializedName(value = "post", alternate = {"user","follower","following","comment"})
    private T data;
    private Pagination pagination;
}
