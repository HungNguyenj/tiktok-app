package com.example.tiktokapp.services;

import com.example.tiktokapp.responseModel.User;
import com.example.tiktokapp.requestModel.LoginReq;
import com.example.tiktokapp.requestModel.SignUpReq;
import com.example.tiktokapp.requestModel.SignUpRes;
import com.example.tiktokapp.requestModel.VerifyEmailReq;
import com.example.tiktokapp.requestModel.VerifyEmailRes;

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
