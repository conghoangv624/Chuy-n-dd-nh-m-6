package com.example.group6.dulichdoday.Models;

/**
 * Created by HoangNghiep IT on 17/04/2018.
 */

public class Account {
    private String password;
    private String userName;
    private String type;

    public Account() {
    }

    public Account(String password, String userName, String type) {
        this.password = password;
        this.userName = userName;
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}