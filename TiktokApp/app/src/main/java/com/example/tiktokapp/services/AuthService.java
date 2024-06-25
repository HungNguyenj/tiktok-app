package com.example.tiktokapp.services;

import com.example.tiktokapp.Model.User;
import com.example.tiktokapp.req_res.LoginReq;
import com.example.tiktokapp.req_res.SignUpReq;
import com.example.tiktokapp.req_res.SignUpRes;
import com.example.tiktokapp.req_res.VerifyEmailReq;
import com.example.tiktokapp.req_res.VerifyEmailRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    AuthService excute =APIClient.getClient().create(AuthService.class);
    @POST("auth/login/")
    Call<User> login(@Body LoginReq loginReq);
    @POST("auth/register/")
    Call<SignUpRes> register(@Body SignUpReq signUpReq);
    @POST("auth/verify-email/")
    Call<VerifyEmailRes> vertifyEmail(@Body VerifyEmailReq verifyEmailReq);
}
