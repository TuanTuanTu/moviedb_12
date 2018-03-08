package com.training.vungoctuan.moviedb.screen.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.training.vungoctuan.moviedb.util.Constant;

/**
 * Created by vungoctuan on 3/7/18.
 */
public class MoviesByPeopleActivity extends MoviesActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.loadMovieFromApi(
            Constant.ApiRequestUrl.API_MOVIES_BY_PEOPLE_REQUEST,
            getIntent().getStringExtra(Constant.BUNDLE_PEOPLE_ID));
        mProgressBar.setVisibility(View.VISIBLE);
    }
}
