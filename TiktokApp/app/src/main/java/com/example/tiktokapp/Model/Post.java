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

    public Post(String title, String videoUrl) {
        this.title = title;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}

