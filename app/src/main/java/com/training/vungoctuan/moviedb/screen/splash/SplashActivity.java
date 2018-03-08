package com.training.vungoctuan.moviedb.screen.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.screen.BaseActivity;
import com.training.vungoctuan.moviedb.screen.home.HomeActivity;
import com.training.vungoctuan.moviedb.util.Constant;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class SplashActivity extends BaseActivity implements SplashContract.View {
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mPresenter = new SplashPresenter();
        mPresenter.setView(this);
        mPresenter.loadData();
    }

    @Override
    public void onLoadingSuccess() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(HomeActivity.getInstance(
                    getApplicationContext()));
                finish();
            }
        }, Constant.SPLASH_TIMEOUT);
    }
}
