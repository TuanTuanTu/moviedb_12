package com.training.vungoctuan.moviedb.screen.detail;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View mView;

    @Override
    public void setView(DetailContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
