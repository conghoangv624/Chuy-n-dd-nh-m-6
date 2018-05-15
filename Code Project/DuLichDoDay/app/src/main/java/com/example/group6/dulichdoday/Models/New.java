package com.example.group6.dulichdoday.Models;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class New {
    private String name;
    private String description;
    private String imgProduct;
    private Long nNumberLike;
    private Long nNumberUnlike;
    private Long nNumberComment;
    private boolean isCheckLike;
    private boolean isCheckUnLike;
    private String Key;


    public New() {

    }

    public New(String description, String name, String imgProduct, String Key,Long nNumberLike, Long nNumberUnlike, Long nNumberComment) {
        this.description = description;
        this.name = name;
        this.imgProduct = imgProduct;
        this.Key = Key;
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

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public Long getnNumberLike() {
        return nNumberLike;
    }

    public void setnNumberLike(Long nNumberLike) {
        this.nNumberLike = nNumberLike;
    }

    public Long getnNumberUnlike() {
        return nNumberUnlike;
    }

    public void setnNumberUnlike(Long nNumberUnlike) {
        this.nNumberUnlike = nNumberUnlike;
    }

    public Long getnNumberComment() {
        return nNumberComment;
    }

    public void setnNumberComment(Long nNumberComment) {
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
