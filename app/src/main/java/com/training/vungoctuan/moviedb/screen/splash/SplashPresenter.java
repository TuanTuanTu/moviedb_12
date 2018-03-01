package com.training.vungoctuan.moviedb.screen.splash;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View mView;

    @Override
    public void setView(SplashContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
