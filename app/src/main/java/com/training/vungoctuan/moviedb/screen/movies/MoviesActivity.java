package com.training.vungoctuan.moviedb.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.screen.BaseActivity;
import com.training.vungoctuan.moviedb.util.Constant;

import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class MoviesActivity extends BaseActivity implements MoviesContract.View {
    MoviesContract.Presenter mPresenter;
    private RecyclerView mRecyclerMoviesResult;
    ProgressBar mProgressBar;
    private MoviesAdapter mMoviesAdapter;
    private static Intent mIntent;

    public static Intent getInstance(Context context, String peopleID, Class movieClass) {
        if (mIntent == null) {
            mIntent = new Intent(context, movieClass);
        }
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra(Constant.BUNDLE_PEOPLE_ID, peopleID);
        return mIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        mPresenter = new MoviesPresenter();
        mPresenter.setView(this);
        mMoviesAdapter = new MoviesAdapter(this);
        initLayout();
    }

    private void initLayout() {
        mRecyclerMoviesResult = findViewById(R.id.recycler_movies_result);
        mProgressBar = findViewById(R.id.progressbar_recycler);
        mRecyclerMoviesResult.setAdapter(mMoviesAdapter);
    }

    @Override
    public void onGetMoviesSuccess(List<Movie> movies) {
        mProgressBar.setVisibility(View.GONE);
        mMoviesAdapter.updateData(movies);
    }
}
