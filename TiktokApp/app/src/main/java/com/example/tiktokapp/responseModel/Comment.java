package com.example.tiktokapp.responseModel;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Comment{
    private int id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int commenter;
    private int postId;
    private String content;
    private int likes;
    @SerializedName("isLiked")
    private boolean isLiked;
    private User commenterData;

    public Comment(int id, Timestamp createdAt, Timestamp updatedAt, int commenter, int postId, String content, int likes, boolean isLiked, User commenterData) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.commenter = commenter;
        this.postId = postId;
        this.content = content;
        this.likes = likes;
        this.isLiked = isLiked;
        this.commenterData = commenterData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getCommenter() {
        return commenter;
    }

    public void setCommenter(int commenter) {
        this.commenter = commenter;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public User getCommenterData() {
        return commenterData;
    }

    public void setCommenterData(User commenterData) {
        this.commenterData = commenterData;
    }
}
