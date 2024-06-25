package com.example.tiktokapp.model;

import java.sql.Timestamp;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
public class Avatar extends AbstractModel{
    private String publicId;
    private String url;
    private String code;

    public Avatar(int id, Timestamp createdAt, Timestamp updatedAt, String publicId, String url, String code) {
        super(id, createdAt, updatedAt);
        this.publicId = publicId;
        this.url = url;
        this.code = code;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
