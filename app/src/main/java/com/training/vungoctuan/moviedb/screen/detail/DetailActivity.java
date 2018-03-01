package com.training.vungoctuan.moviedb.screen.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.screen.BaseActivity;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class DetailActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
