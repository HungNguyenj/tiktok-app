package com.example.tiktokapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Follow extends AbstractModel{
    private final int follower;
    private final int followee;
    private User followerData;
    private User followeeData;
}
