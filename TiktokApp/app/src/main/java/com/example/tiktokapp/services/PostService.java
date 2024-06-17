package com.example.tiktokapp.services;

import com.example.tiktokapp.Model.APIRespone;
import com.example.tiktokapp.Model.Post;

import retrofit2.Call;

public interface PostService {
    PostService excute =APIClient.getClient().create(PostService.class);
    Call<APIRespone<Post>> getPosts();
}
