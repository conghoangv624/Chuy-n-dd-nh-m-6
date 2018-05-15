package com.example.group6.dulichdoday.Models;

/**
 * Created by HoangNghiep IT on 17/04/2018.
 */

public class UserInfor {
    private String address;
    private String email;
    private String userID;
    private String name;
    private String phoneNumber;
    private String imageUrl;
    private String userType;



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    private String userPass;

    public UserInfor() {
    }

    public UserInfor(String address, String email, String name, String phoneNumber, String imageUrl, String userType, String userPass, String userID) {
        this.address = address;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.userType = userType;
        this.userPass = userPass;
        this.userID = userID;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
