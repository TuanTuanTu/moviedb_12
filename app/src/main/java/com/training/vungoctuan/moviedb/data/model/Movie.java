package com.training.vungoctuan.moviedb.data.model;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class Movie {
    private String mTitle;
    private String mVoteAverage;
    private String mPosterPath;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }
}
