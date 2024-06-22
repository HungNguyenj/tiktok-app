package com.example.tiktokapp.Model;

import java.sql.Timestamp;

public class Post extends AbstractModel{
    private String title;
    private int visibility;
    private String videoUrl;
    private String videoId;
    private String thumnailUrl;
    private String thumnailId;
    private int comments;
    private int views;
    private int shares;
    private int likes;
    private int poster;
    private User posterData;

    public Post(int comments, int likes, int poster, User posterData, int shares, String thumnailId, String thumnailUrl, String title, String videoId, String videoUrl, int views, int visibility) {
        this.comments = comments;
        this.likes = likes;
        this.poster = poster;
        this.posterData = posterData;
        this.shares = shares;
        this.thumnailId = thumnailId;
        this.thumnailUrl = thumnailUrl;
        this.title = title;
        this.videoId = videoId;
        this.videoUrl = videoUrl;
        this.views = views;
        this.visibility = visibility;
    }

    public Post(){

    }

    public Post(String title, String videoUrl) {
        this.title = title;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public void setPosterData(User posterData) {
        this.posterData = posterData;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public void setThumnailId(String thumnailId) {
        this.thumnailId = thumnailId;
    }

    public void setThumnailUrl(String thumnailUrl) {
        this.thumnailUrl = thumnailUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}

