package com.example.shailaja.multipurposehome.pojoclass;

import java.io.Serializable;

public class Home implements Serializable {

    private String title, details, imageurl;
    int categoryId;

    public Home() {
    }

    public Home(String title, String details, String imageurl, int categoryId) {
        this.title = title;
        this.details = details;
        this.imageurl = imageurl;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}