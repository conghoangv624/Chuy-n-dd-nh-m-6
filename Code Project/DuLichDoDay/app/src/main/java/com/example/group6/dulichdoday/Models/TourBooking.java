package com.example.group6.dulichdoday.Models;

/**
 * Created by HoangNghiep IT on 17/04/2018.
 */

public class TourBooking {
    private String tourBook_ID;
    private String account_ID;
    private String tour_ID;

    public TourBooking() {
    }

    public TourBooking(String tourBook_ID, String account_ID, String tour_ID) {
        this.tourBook_ID = tourBook_ID;
        this.account_ID = account_ID;
        this.tour_ID = tour_ID;
    }

    public String getTourBook_ID() {
        return tourBook_ID;
    }

    public void setTourBook_ID(String tourBook_ID) {
        this.tourBook_ID = tourBook_ID;
    }

    public String getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(String account_ID) {
        this.account_ID = account_ID;
    }

    public String getTour_ID() {
        return tour_ID;
    }

    public void setTour_ID(String tour_ID) {
        this.tour_ID = tour_ID;
    }
}
