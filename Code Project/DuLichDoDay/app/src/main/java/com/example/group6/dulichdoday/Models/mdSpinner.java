package com.example.group6.dulichdoday.Models;

/**
 * Created by DELL on 4/18/2018.
 */

public class mdSpinner {
    private int IMG;
    private String txt;

    public mdSpinner() {
    }

    public mdSpinner(int IMG, String txt) {
        this.IMG = IMG;
        this.txt = txt;
    }

    public int getIMG() {
        return IMG;
    }

    public void setIMG(int IMG) {
        this.IMG = IMG;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
