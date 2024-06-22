package com.example.tiktokapp.services;

import com.example.tiktokapp.Model.APIRespone;
import com.example.tiktokapp.Model.Post;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    PostService excute =APIClient.getClient().create(PostService.class);

    @GET("post")
    Call<APIRespone<Post>> getPosts();
}
