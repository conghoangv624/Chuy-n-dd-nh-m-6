package com.example.group6.dulichdoday.Models;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class New {
    private String name;
    private String description;
    private String imgProduct;
    private String nNumberLike;
    private String nNumberUnlike;
    private String nNumberComment;
    private boolean isCheckLike;
    private boolean isCheckUnLike;


    public New() {

    }

    public New(String description, String name, String imgProduct, String nNumberLike, String nNumberUnlike, String nNumberComment) {
        this.description = description;
        this.name = name;
        this.imgProduct = imgProduct;
        this.nNumberLike = nNumberLike;
        this.nNumberUnlike = nNumberUnlike;
        this.nNumberComment = nNumberComment;
        this.isCheckLike = isCheckLike;
        this.isCheckUnLike = isCheckUnLike;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getnNumberLike() {
        return nNumberLike;
    }

    public void setnNumberLike(String nNumberLike) {
        this.nNumberLike = nNumberLike;
    }

    public String getnNumberUnlike() {
        return nNumberUnlike;
    }

    public void setnNumberUnlike(String nNumberUnlike) {
        this.nNumberUnlike = nNumberUnlike;
    }

    public String getnNumberComment() {
        return nNumberComment;
    }

    public void setnNumberComment(String nNumberComment) {
        this.nNumberComment = nNumberComment;
    }

    public boolean isCheckLike() {
        return isCheckLike;
    }

    public void setCheckLike(boolean checkLike) {
        isCheckLike = checkLike;
    }

    public boolean isCheckUnLike() {
        return isCheckUnLike;
    }

    public void setCheckUnLike(boolean checkUnLike) {
        isCheckUnLike = checkUnLike;
    }
}