package com.example.group6.dulichdoday.Models;

/**
 * Created by HoangNghiep IT on 17/04/2018.
 */

public class UserInfor {
    private String address;
    private String email;
    private String name;
    private String phoneNumber;
    private String imageUrl;

    public UserInfor() {
    }

    public UserInfor(String address, String email, String name, String phoneNumber, String imageUrl) {
        this.address = address;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
