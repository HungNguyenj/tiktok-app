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

    public Post(int id, Timestamp createdAt, Timestamp updatedAt, String title, String videoUrl, int visibility, String videoId, String thumnailUrl, String thumnailId, int comments, int views, int shares, int likes, int poster, User posterData, int isFollow, int isLiked, int isMe, int isFriend) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.videoUrl = videoUrl;
        this.visibility = visibility;
        this.videoId = videoId;
        this.thumnailUrl = thumnailUrl;
        this.thumnailId = thumnailId;
        this.comments = comments;
        this.views = views;
        this.shares = shares;
        this.likes = likes;
        this.poster = poster;
        this.posterData = posterData;
        this.isFollow = isFollow;
        this.isLiked = isLiked;
        this.isMe = isMe;
        this.isFriend = isFriend;
    }

    public String getTitle() {
        return title;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getThumnailUrl() {
        return thumnailUrl;
    }

    public void setThumnailUrl(String thumnailUrl) {
        this.thumnailUrl = thumnailUrl;
    }

    public String getThumnailId() {
        return thumnailId;
    }

    public void setThumnailId(String thumnailId) {
        this.thumnailId = thumnailId;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public User getPosterData() {
        return posterData;
    }

    public void setPosterData(User posterData) {
        this.posterData = posterData;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public int getIsMe() {
        return isMe;
    }

    public void setIsMe(int isMe) {
        this.isMe = isMe;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }
}

