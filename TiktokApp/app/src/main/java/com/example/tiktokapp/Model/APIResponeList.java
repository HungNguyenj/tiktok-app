package com.example.tiktokapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class APIResponeList<T extends AbstractModel> {
    private int err;
    private String mes;
    @SerializedName(value = "posts", alternate = {"users","followers","followings","comments"})
    private List<T> data;
    private Pagination pagination;
}
