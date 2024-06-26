package com.example.tiktokapp.services;

import com.example.tiktokapp.responseModel.APIRespone;
import com.example.tiktokapp.responseModel.Follow;
import com.example.tiktokapp.responseModel.SimpleAPIRespone;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FollowService {

    @POST("follow/{userId}")
    Call<APIRespone<Follow>> follow(@Path("userId") int userId);

    @DELETE("follow/{userId}")
    Call<SimpleAPIRespone> unFollow(@Path("userId") int userId);

}
