package com.example.tiktokapp.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Role extends AbstractModel{
    private String code;
    private String value;
}
