package com.example.group6.dulichdoday.Models;

/**
 * Created by HoangNghiep IT on 18/04/2018.
 */

public class Images {
    private String imgName;
    private String imgLink;

    public Images() {
    }

    public Images(String imgName, String imgLink) {
        this.imgName = imgName;
        this.imgLink = imgLink;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
