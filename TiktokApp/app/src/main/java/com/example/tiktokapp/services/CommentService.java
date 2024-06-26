package com.example.tiktokapp.services;

import com.example.tiktokapp.model.APIResponeList;
import com.example.tiktokapp.model.Comment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentService {
    CommentService excute = APIClient.getClient().create(CommentService.class);

    @GET("posts/{postId}/comments")
    Call<APIResponeList<Comment>> getComments(@Path("postId") int postId);

    @POST("posts/{postId}/comments")
    Call<Comment> postComment(@Path("postId") int postId, @Body Comment comment);
}
