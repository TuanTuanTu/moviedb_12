package com.training.vungoctuan.moviedb.screen.home;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class HomePresenter implements HomeContract.Presenter{
    private HomeContract.View mView;

    @Override
    public void setView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadPopularMovies() {

    }
}
