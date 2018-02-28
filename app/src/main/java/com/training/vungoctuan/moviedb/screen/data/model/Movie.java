package com.training.vungoctuan.moviedb.screen.data.model;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class Movie {
    private String mName;
    private String mRate;
    private String mImage;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRate() {
        return mRate;
    }

    public void setRate(String rate) {
        mRate = rate;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
