package com.training.vungoctuan.moviedb.screen.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.screen.BaseActivity;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class MoviesActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
    }
}
