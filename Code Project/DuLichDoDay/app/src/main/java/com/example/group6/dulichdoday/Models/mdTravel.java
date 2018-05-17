package com.example.group6.dulichdoday.Models;

/**
 * Created by DELL on 5/16/2018.
 */

public class mdTravel {
    private String nameTravel;
    private String addressTravel;
    private double lan;
    private double log;


    public mdTravel() {

        // Return
    }

    public mdTravel(String nameTravel, String addressTravel, double lan, double log) {
        this.nameTravel = nameTravel;
        this.addressTravel = addressTravel;
        this.lan = lan;
        this.log = log;
    }

    public String getNameTravel() {
        return nameTravel;
    }

    public void setNameTravel(String nameTravel) {
        this.nameTravel = nameTravel;
    }

    public String getAddressTravel() {
        return addressTravel;
    }

    public void setAddressTravel(String addressTravel) {
        this.addressTravel = addressTravel;
    }

    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
}
