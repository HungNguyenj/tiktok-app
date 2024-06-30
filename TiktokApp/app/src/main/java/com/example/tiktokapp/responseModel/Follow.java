package com.example.tiktokapp.responseModel;

import java.sql.Timestamp;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Follow extends AbstractModel{
    private final int follower;
    private final int followee;
    private User followerData;
    private User followeeData;

    public Follow(int id, Timestamp createdAt, Timestamp updatedAt, int follower, int followee, User followerData, User followeeData) {
        super(id, createdAt, updatedAt);
        this.follower = follower;
        this.followee = followee;
        this.followerData = followerData;
        this.followeeData = followeeData;
    }

    public int getFollower() {
        return follower;
    }

    public int getFollowee() {
        return followee;
    }

    public User getFollowerData() {
        return followerData;
    }

    public void setFollowerData(User followerData) {
        this.followerData = followerData;
    }

    public User getFolloweeData() {
        return followeeData;
    }

    public void setFolloweeData(User followeeData) {
        this.followeeData = followeeData;
    }
}
