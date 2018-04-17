package com.example.group6.dulichdoday.Models;

/**
 * Created by HoangNghiep IT on 17/04/2018.
 */

public class Province {
   private String province_ID;
   private String provinceName;
   private String longitude;
    private String latitude;

    public Province() {
    }

    public Province(String province_ID, String provinceName, String longitude, String latitude) {
        this.province_ID = province_ID;
        this.provinceName = provinceName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getProvince_ID() {
        return province_ID;
    }

    public void setProvince_ID(String province_ID) {
        this.province_ID = province_ID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
