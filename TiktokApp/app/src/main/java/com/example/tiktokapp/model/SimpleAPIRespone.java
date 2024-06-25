package com.example.tiktokapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SimpleAPIRespone {
    private int err;
    private String mes;


}
