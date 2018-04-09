package com.example.group6.dulichdoday.Models;

/**
 * Created by TRILE on 16/03/2018.
 */

public class Comment {

    private int imgUserComment;
    private String tvUserComment;

    public Comment() {
        // Return
    }

    public Comment(int imgUserComment, String tvUserComment) {
        this.imgUserComment = imgUserComment;
        this.tvUserComment = tvUserComment;
    }

    public int getImgUserComment() {
        return imgUserComment;
    }

    public void setImgUserComment(int imgUserComment) {
        this.imgUserComment = imgUserComment;
    }

    public String getTvUserComment() {
        return tvUserComment;
    }

    public void setTvUserComment(String tvUserComment) {
        this.tvUserComment = tvUserComment;
    }
}
