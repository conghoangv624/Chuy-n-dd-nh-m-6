package com.example.group6.dulichdoday.Models;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class Tour {
    private int imgProduct;
    private String codeTour;
    private String addTour;
    private String discripTour;
    private String priceTour;
    private String nNumberLike;
    private String nNumberUnlike;
    private String nNumberComment;
    private boolean isCheckLike;
    private boolean isCheckUnLike;


    public Tour() {
        // Return
    }

    public Tour(int imgProduct, String codeTour, String addTour, String discripTour, String priceTour, String nNumberLike, String nNumberUnlike, String nNumberComment) {
        this.imgProduct = imgProduct;
        this.codeTour = codeTour;
        this.addTour = addTour;
        this.discripTour = discripTour;
        this.priceTour = priceTour;
        this.nNumberLike = nNumberLike;
        this.nNumberUnlike = nNumberUnlike;
        this.nNumberComment = nNumberComment;
        this.isCheckLike = isCheckLike;
        this.isCheckUnLike = isCheckUnLike;
    }

    public int getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(int imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getCodeTour() {
        return codeTour;
    }

    public void setCodeTour(String codeTour) {
        this.codeTour = codeTour;
    }

    public String getAddTour() {
        return addTour;
    }

    public void setAddTour(String addTour) {
        this.addTour = addTour;
    }

    public String getDiscripTour() {
        return discripTour;
    }

    public void setDiscripTour(String discripTour) {
        this.discripTour = discripTour;
    }

    public String getPriceTour() {
        return priceTour;
    }

    public void setPriceTour(String priceTour) {
        this.priceTour = priceTour;
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
