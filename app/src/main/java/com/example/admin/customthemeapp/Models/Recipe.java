package com.example.admin.customthemeapp.Models;

import com.google.gson.annotations.SerializedName;

public class Recipe {

        @SerializedName("label")
        private String title;

        @SerializedName("image")
        private String  imgUrl;

        @SerializedName("totalTime")
        private double totalTime;

        @SerializedName("calories")
        private double calories;

        @SerializedName("url")
        private String fullInfoUrl;

    public String getFullInfoUrl() {
        return fullInfoUrl;
    }

    public void setFullInfoUrl(String fullInfoUrl) {
        this.fullInfoUrl = fullInfoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
