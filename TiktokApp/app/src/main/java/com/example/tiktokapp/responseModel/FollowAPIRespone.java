package com.example.tiktokapp.responseModel;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class FollowAPIRespone extends SimpleAPIRespone {
    private Follow follow;

    public FollowAPIRespone(int err, String mes, Follow follow) {
        super(err, mes);
        this.follow = follow;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }
}
