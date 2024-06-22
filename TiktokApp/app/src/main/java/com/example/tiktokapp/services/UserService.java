package com.example.tiktokapp.services;

import com.example.tiktokapp.Model.User;
import com.example.tiktokapp.req_res.LoginReq;
import com.example.tiktokapp.req_res.SignUpReq;
import com.example.tiktokapp.req_res.SignUpRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("auth/login/")
    Call<User> userLogin(@Body LoginReq loginReq);
    @POST("auth/register/")
    Call<SignUpRes> userSignUp(@Body SignUpReq signUpReq);
}
