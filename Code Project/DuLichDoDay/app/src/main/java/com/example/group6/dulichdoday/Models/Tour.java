package com.example.group6.dulichdoday.Models;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class Tour {
    private String imgProduct;
    private String codeTour;
    private String addTour;
    private String discripTour;
    private String priceTour;
    private String tenmien;

    public Tour() {
    }

    public Tour(String imgProduct, String codeTour, String addTour, String discripTour, String priceTour, String tenmien) {
        this.imgProduct = imgProduct;
        this.codeTour = codeTour;
        this.addTour = addTour;
        this.discripTour = discripTour;
        this.priceTour = priceTour;
        this.tenmien = tenmien;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
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

    public String getTenmien() {
        return tenmien;
    }

    public void setTenmien(String tenmien) {
        this.tenmien = tenmien;
    }
}

