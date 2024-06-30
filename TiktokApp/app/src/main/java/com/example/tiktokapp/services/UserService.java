package com.example.tiktokapp.services;

import com.example.tiktokapp.responseModel.APIRespone;
import com.example.tiktokapp.responseModel.Follow;
import com.example.tiktokapp.responseModel.FollowAPIRespone;
import com.example.tiktokapp.responseModel.SimpleAPIRespone;
import com.example.tiktokapp.responseModel.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("user/me")
    Call<APIRespone<User>> me();
}
