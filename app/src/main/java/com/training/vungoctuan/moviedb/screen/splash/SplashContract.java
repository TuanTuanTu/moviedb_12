package com.training.vungoctuan.moviedb.screen.splash;

import com.training.vungoctuan.moviedb.screen.BasePresenter;

/**
 * Created by vungoctuan on 2/28/18.
 */
public interface SplashContract {
    interface View {
        void onLoadingSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void loadData();
    }
}
