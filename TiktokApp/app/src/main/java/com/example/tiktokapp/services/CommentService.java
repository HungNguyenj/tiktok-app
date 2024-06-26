package com.example.tiktokapp.services;

import com.example.tiktokapp.responseModel.APIResponeList;
import com.example.tiktokapp.responseModel.Comment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentService {

    @GET("comment/post/{postId}")
    Call<APIResponeList<Comment>> getComments(@Path("postId") int postId);

}
