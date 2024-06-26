package com.example.tiktokapp.services;

import com.example.tiktokapp.responseModel.APIResponeList;
import com.example.tiktokapp.responseModel.FollowAPIRespone;
import com.example.tiktokapp.responseModel.Post;
import com.example.tiktokapp.responseModel.SimpleAPIRespone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostService {

    @GET("post")
    Call<APIResponeList<Post>> getPosts();

    @POST("post/like/{postId}")
    Call<SimpleAPIRespone> likePost(@Path("postId") int postId);

    @POST("post/unlike/{postId}")
    Call<SimpleAPIRespone> unlikePost(@Path("postId") int postId);
}
