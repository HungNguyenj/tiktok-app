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
}
