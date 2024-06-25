package com.example.tiktokapp.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User extends AbstractModel implements Serializable  {
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

    public User(String userName, String fullName, String peerId, String accessToken, String email, String password, String association, Avatar avatarData, Role roleData){
        this.userName = userName;
        this.fullName = fullName;
        this.peerId = peerId;
        this.accessToken = accessToken;
        this.email = email;
        this.password = password;
        this.association = association;
        this.avatarData = avatarData;
        this.roleData = roleData;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPeerId() {
        return peerId;
    }

    public void setPeerId(String peerId) {
        this.peerId = peerId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public Avatar getAvatarData() {
        return avatarData;
    }

    public void setAvatarData(Avatar avatarData) {
        this.avatarData = avatarData;
    }

    public Role getRoleData() {
        return roleData;
    }

    public void setRoleData(Role roleData) {
        this.roleData = roleData;
    }

    public boolean isVertified() {
        return isVertified;
    }

    public void setVertified(boolean vertified) {
        isVertified = vertified;
    }
}