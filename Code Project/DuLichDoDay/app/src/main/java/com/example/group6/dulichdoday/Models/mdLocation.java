package com.example.group6.dulichdoday.Models;

/**
 * Created by DELL on 4/26/2018.
 */

public class mdLocation {
    private String strNameAđ;
    private double latitude;
    private double longitude;

    public mdLocation(String strNameAđ, double latitude, double longitude) {
        this.strNameAđ = strNameAđ;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public mdLocation() {
        //
    }

    public String getStrNameAđ() {
        return strNameAđ;
    }

    public void setStrNameAđ(String strNameAđ) {
        this.strNameAđ = strNameAđ;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
