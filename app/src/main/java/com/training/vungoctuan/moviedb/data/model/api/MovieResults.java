package com.training.vungoctuan.moviedb.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.util.Constant;

import java.util.List;

/**
 * Created by vungoctuan on 3/19/18.
 */
public class MovieResults {
    @SerializedName(Constant.ApiResultKey.API_KEY_RESULTS)
    @Expose
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }
}
