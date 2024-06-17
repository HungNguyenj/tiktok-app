package com.example.tiktokapp.Model;

import java.sql.Timestamp;

public class User extends AbstractModel{
    private String userName;
    private String fullName;
    private String peerId;
    private String accessToken;
    private String email;
    private String password;
    private String association;
    private Avatar avatarData;
    private Role roleData;
    private boolean isVertified;
}
