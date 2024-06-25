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
    PostService excute = APIClient.getClient().create(PostService.class);
    @GET("post")
    Call<APIResponeList<Post>> getPosts();

    @Headers({
            "Token:  Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJob2FuZ2h1eWRldkBnbWFpbC5jb20iLCJyb2xlVmFsdWUiOiJVc2VyIiwiaWF0IjoxNzE5MzA0OTAxLCJleHAiOjE3MTk0Nzc3MDF9.PoHkDr_QZkvN-plb-RKdCBHozX_RbL7zQkcmrV-TTHd_9y14dJ9fDBclmAwVqnncghL2damD_3T03DDFwskWxB1uu_3XrrhGbIQ6yqABK9OL55rCGsFdIHLCQmZkI-3hoPqV5vGIMXXB2XBlJ_Yd8EZRGCdiOIe4FFBGINCKsv_qFm6iRYm5jzNSwb3FBH1DyvDvY-A5qQIVH7emeKY9IIjcnMu3s3sciMTZsmMYcN4_sxGC6PM9UIe7INhnq8jetncMz6HEJ2hNGxmKGP3aGx3_t3jPeeAllNO6tdVPk7auYghmqByaaQ2h9DxWfdDXyThfgI-CU4Dj3MAsDtu_AA " /// Thêm access token ở đây
    })
    @POST("post/like/{postId}")
    Call<SimpleAPIRespone> likePost(@Path("postId") int postId);

    @Headers({
            "Token:  Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJob2FuZ2h1eWRldkBnbWFpbC5jb20iLCJyb2xlVmFsdWUiOiJVc2VyIiwiaWF0IjoxNzE5MzA0OTAxLCJleHAiOjE3MTk0Nzc3MDF9.PoHkDr_QZkvN-plb-RKdCBHozX_RbL7zQkcmrV-TTHd_9y14dJ9fDBclmAwVqnncghL2damD_3T03DDFwskWxB1uu_3XrrhGbIQ6yqABK9OL55rCGsFdIHLCQmZkI-3hoPqV5vGIMXXB2XBlJ_Yd8EZRGCdiOIe4FFBGINCKsv_qFm6iRYm5jzNSwb3FBH1DyvDvY-A5qQIVH7emeKY9IIjcnMu3s3sciMTZsmMYcN4_sxGC6PM9UIe7INhnq8jetncMz6HEJ2hNGxmKGP3aGx3_t3jPeeAllNO6tdVPk7auYghmqByaaQ2h9DxWfdDXyThfgI-CU4Dj3MAsDtu_AA " /// Thêm access token ở đây
    })
    @POST("post/unlike/{postId}")
    Call<SimpleAPIRespone> unlikePost(@Path("postId") int postId);
    @Headers({
            "Token:  Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJob2FuZ2h1eWRldkBnbWFpbC5jb20iLCJyb2xlVmFsdWUiOiJVc2VyIiwiaWF0IjoxNzE5MzA0OTAxLCJleHAiOjE3MTk0Nzc3MDF9.PoHkDr_QZkvN-plb-RKdCBHozX_RbL7zQkcmrV-TTHd_9y14dJ9fDBclmAwVqnncghL2damD_3T03DDFwskWxB1uu_3XrrhGbIQ6yqABK9OL55rCGsFdIHLCQmZkI-3hoPqV5vGIMXXB2XBlJ_Yd8EZRGCdiOIe4FFBGINCKsv_qFm6iRYm5jzNSwb3FBH1DyvDvY-A5qQIVH7emeKY9IIjcnMu3s3sciMTZsmMYcN4_sxGC6PM9UIe7INhnq8jetncMz6HEJ2hNGxmKGP3aGx3_t3jPeeAllNO6tdVPk7auYghmqByaaQ2h9DxWfdDXyThfgI-CU4Dj3MAsDtu_AA" /// Thêm access token ở đây
    })
    @POST("follow/{userId}")
    Call<SimpleAPIRespone> follow(@Path("userId") int userId);

    @Headers({
            "Token:  Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJob2FuZ2h1eWRldkBnbWFpbC5jb20iLCJyb2xlVmFsdWUiOiJVc2VyIiwiaWF0IjoxNzE5MzA0OTAxLCJleHAiOjE3MTk0Nzc3MDF9.PoHkDr_QZkvN-plb-RKdCBHozX_RbL7zQkcmrV-TTHd_9y14dJ9fDBclmAwVqnncghL2damD_3T03DDFwskWxB1uu_3XrrhGbIQ6yqABK9OL55rCGsFdIHLCQmZkI-3hoPqV5vGIMXXB2XBlJ_Yd8EZRGCdiOIe4FFBGINCKsv_qFm6iRYm5jzNSwb3FBH1DyvDvY-A5qQIVH7emeKY9IIjcnMu3s3sciMTZsmMYcN4_sxGC6PM9UIe7INhnq8jetncMz6HEJ2hNGxmKGP3aGx3_t3jPeeAllNO6tdVPk7auYghmqByaaQ2h9DxWfdDXyThfgI-CU4Dj3MAsDtu_AA " /// Thêm access token ở đây
    })
    @POST("unfollow/{userId}")
    Call<FollowAPIRespone> unFollow(@Path("userId") int userId);


}
