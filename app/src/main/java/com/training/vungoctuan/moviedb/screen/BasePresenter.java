package com.training.vungoctuan.moviedb.screen;

/**
 * Created by vungoctuan on 2/28/18.
 */
public interface BasePresenter<T> {
    void setView(T view);
    void onStart();
    void onStop();
}
