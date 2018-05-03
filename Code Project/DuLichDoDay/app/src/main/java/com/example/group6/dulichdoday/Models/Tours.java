package com.example.group6.dulichdoday.Models;

/**
 * Created by HoangNghiep IT on 17/04/2018.
 */

public class Tours {
    private String tour_ID;
    private String account_ID;
    private String tourName;
    private String tourTime;
    private String tourPrice;
    private String tourDescription;
    private String imgTour;
    private String tenMien;
    private boolean ischecked;



    public Tours() {
    }

    public Tours(String tour_ID, String account_ID, String tourName, String tourTime, String tourPrice, String tourDescription, String imgTour, String tenMien, boolean ischecked) {
        this.tour_ID = tour_ID;
        this.account_ID = account_ID;
        this.tourName = tourName;
        this.tourTime = tourTime;
        this.tourPrice = tourPrice;
        this.tourDescription = tourDescription;
        this.imgTour = imgTour;
        this.tenMien = tenMien;
        this.ischecked = ischecked;
    }

    public String getTour_ID() {
        return tour_ID;
    }

    public void setTour_ID(String tour_ID) {
        this.tour_ID = tour_ID;
    }

    public String getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(String account_ID) {
        this.account_ID = account_ID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourTime() {
        return tourTime;
    }

    public void setTourTime(String tourTime) {
        this.tourTime = tourTime;
    }

    public String getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(String tourPrice) {
        this.tourPrice = tourPrice;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public String getImgTour() {
        return imgTour;
    }

    public void setImgTour(String imgTour) {
        this.imgTour = imgTour;
    }

    public String getTenMien() {
        return tenMien;
    }

    public void setTenMien(String tenMien) {
        this.tenMien = tenMien;
    }
    public boolean isIschecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }
}
