package com.example.tiktokapp.model;

import java.sql.Timestamp;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Role extends AbstractModel{
    private String code;
    private String value;

    public Role(int id, Timestamp createdAt, Timestamp updatedAt, String code, String value) {
        super(id, createdAt, updatedAt);
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
