package com.training.vungoctuan.moviedb.screen.movies;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class MoviesPresenter implements MoviesContract.Presenter {
    private MoviesContract.View mView;

    @Override
    public void setView(MoviesContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
