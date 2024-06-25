package com.example.tiktokapp.model;

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
    private boolean isFollow;
     boolean isLiked;
    private boolean isMe;

    public Post(int id, Timestamp createdAt, Timestamp updatedAt, String title, String videoUrl, int visibility, String videoId, String thumnailUrl, String thumnailId, int comments, int views, int shares, int likes, int poster, User posterData, boolean isFollow, boolean isLiked, boolean isMe) {
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

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }
}

