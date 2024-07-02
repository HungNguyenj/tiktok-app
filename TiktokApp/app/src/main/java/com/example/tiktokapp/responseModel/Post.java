package com.example.tiktokapp.responseModel;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Post extends AbstractModel{
    private final String title;
    private int visibility;
    private final String videoUrl;
    private String videoId;
    private String thumnailUrl;
    private String thumnailId;
    private int comments;
    private int views;
    private int shares;
    private int likes;
    private int poster;
    @SerializedName("posterData")
    private User posterData;
    @SerializedName("isFollow")
    private int isFollow;
    @SerializedName("isLiked")
    private int isLiked;
    @SerializedName("isMe")
    private int isMe;
    @SerializedName("isFriend")
    private int isFriend;


    public String getTitle() {
        return title;
    }

    public int getVisibility() {
        return visibility;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getThumnailUrl() {
        return thumnailUrl;
    }

    public String getThumnailId() {
        return thumnailId;
    }

    public int getComments() {
        return comments;
    }

    public int getViews() {
        return views;
    }

    public int getShares() {
        return shares;
    }

    public int getLikes() {
        return likes;
    }

    public int getPoster() {
        return poster;
    }

    public User getPosterData() {
        return posterData;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public int getIsLiked() {
        return isLiked;
    }

    public int getIsMe() {
        return isMe;
    }

    public int getIsFriend() {
        return isFriend;
    }
}

