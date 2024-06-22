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
}
