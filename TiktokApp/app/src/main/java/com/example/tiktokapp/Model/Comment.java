package com.example.tiktokapp.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment extends AbstractModel{
    private final int commenter;
    private final int postId;
    private final String content;
    private final int likes;
    private final boolean isLiked;
    private User commenterData;

    // Constructors
    public Comment(int commenter, int postId, String content, int likes, boolean isLiked, User commenterData) {
        this.postId = postId;
        this.commenter = commenter;
        this.content = content;
        this.likes = likes;
        this.isLiked = isLiked;
        this.commenterData = commenterData;
    }

    public Comment(int commenter, int postId, String content) {
        this.commenter = commenter;
        this.postId = postId;
        this.content = content;
    }


    public User getCommenterId() {
        return commenterData;
    }

    public String getContent() {
        return content;
    }

    public int getTimestamp() {
        return 1;
    }
}