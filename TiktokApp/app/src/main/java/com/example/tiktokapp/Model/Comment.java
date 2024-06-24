package com.example.tiktokapp.Model;

import lombok.Data;

@Data
public class Comment {
    private int id;
    private int commenterId;
    private int postId;
    private String content;
    private int likes;
    private boolean isLiked;
    private User commenterData;
    private String timestamp; // Assuming you need a timestamp for each comment

    // Constructors
    public Comment(int commenterId, int postId, String content) {
        this.commenterId = commenterId;
        this.postId = postId;
        this.content = content;
    }

    public Comment(int id, int commenterId, int postId, String content, int likes, boolean isLiked, User commenterData, String timestamp) {
        this.id = id;
        this.commenterId = commenterId;
        this.postId = postId;
        this.content = content;
        this.likes = likes;
        this.isLiked = isLiked;
        this.commenterData = commenterData;
        this.timestamp = timestamp;
    }
}
