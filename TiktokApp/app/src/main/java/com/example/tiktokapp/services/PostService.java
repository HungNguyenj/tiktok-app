package com.example.tiktokapp.services;

import com.example.tiktokapp.model.APIResponeList;
import com.example.tiktokapp.model.Post;
import com.example.tiktokapp.model.SimpleAPIRespone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostService {
    PostService excute = APIClient.getClient().create(PostService.class);
    @GET("post")
    Call<APIResponeList<Post>> getPosts();

    @Headers({
            "Token: access token " /// Thêm access token ở đây
    })
    @POST("/post/like")
    Call<SimpleAPIRespone> likePost(@Path("postId") int postId);

    @Headers({
            "Token: access token " /// Thêm access token ở đây
    })
    @POST("/post/unlike")
    Call<SimpleAPIRespone> unlikePost(@Path("postId") int postId);

}
