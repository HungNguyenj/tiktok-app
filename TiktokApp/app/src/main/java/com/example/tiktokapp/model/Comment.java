package com.example.tiktokapp.model;

import java.sql.Timestamp;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Comment extends AbstractModel{
    private final int commenter;
    private final int postId;
    private final String content;
    private final int likes;
    private final boolean isLiked;
    private User commenterData;

    public Comment(int id, Timestamp createdAt, Timestamp updatedAt, int commenter, int postId, String content, int likes, boolean isLiked, User commenterData) {
        super(id, createdAt, updatedAt);
        this.commenter = commenter;
        this.postId = postId;
        this.content = content;
        this.likes = likes;
        this.isLiked = isLiked;
        this.commenterData = commenterData;
    }




    public int getCommenter() {
        return commenter;
    }

    public int getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public User getCommenterData() {
        return commenterData;
    }

    public void setCommenterData(User commenterData) {
        this.commenterData = commenterData;
    }

    public String setUsername(String name){
        return commenterData.getUserName();
    }

}