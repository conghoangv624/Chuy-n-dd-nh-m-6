package com.example.group6.dulichdoday.Models;

/**
 * Created by Nhan IT on 4/9/2018.
 */

//public class Comment {
//
//    private int imgUserComment;
//    private String tvUserComment;
//
//    public Comment() {
//        // Return
//    }
//
//    public Comment(int imgUserComment, String tvUserComment) {
//        this.imgUserComment = imgUserComment;
//        this.tvUserComment = tvUserComment;
//    }
//
//    public int getImgUserComment() {
//        return imgUserComment;
//    }
//
//    public void setImgUserComment(int imgUserComment) {
//        this.imgUserComment = imgUserComment;
//    }
//
//    public String getTvUserComment() {
//        return tvUserComment;
//    }
//
//    public void setTvUserComment(String tvUserComment) {
//        this.tvUserComment = tvUserComment;
//    }
//}

public class Comment{
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    //phuong thuc khoi tao co tham so
    public Comment(String key) {
        this.key = key;
    }

    //phuong thuc khoi tao mac dinh
    public Comment() {

    }
}