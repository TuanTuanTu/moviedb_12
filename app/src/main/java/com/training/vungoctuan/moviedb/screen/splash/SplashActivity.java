package com.training.vungoctuan.moviedb.screen.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.screen.BaseActivity;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class SplashActivity extends BaseActivity{
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPresenter = new SplashPresenter();
    }
}
