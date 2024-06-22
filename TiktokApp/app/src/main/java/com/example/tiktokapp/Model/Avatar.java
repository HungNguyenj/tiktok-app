package com.example.tiktokapp.Model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Avatar extends AbstractModel{
    private String publicId;
    private String url;
    private String code;
}
