package com.example.tiktokapp.Model;

public class Avatar extends AbstractModel{
    private String publicId;
    private String url;
    private String code;

    public Avatar(String publicId, String url, String code){
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
