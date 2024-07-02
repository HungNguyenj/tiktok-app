package com.example.tiktokapp.responseModel;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Follow extends AbstractModel{
    private final int follower;
    private final int followee;
    private int isFollowee;
    private int isFollow;
    private int isFriend;
    private User followerData;
    private User followeeData;

}
