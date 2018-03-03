package com.training.vungoctuan.moviedb.screen.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.screen.BaseActivity;

import java.util.List;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class HomeActivity extends BaseActivity implements HomeContract.View {
    private HomeContract.Presenter mPresenter;
    private HomeAdapter mPopularMoviesAdapter, mNowPlayingMoviesAdapter,
        mUpcomingMoviesAdapter, mTopRateMoviesAdapter, mGenresMoviesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mPresenter = new HomePresenter();
        mPresenter.setView(this);
        initMoviesAdapters();
        initLayoutPopular();
        initLayoutNowPlaying();
        initLayoutUpcoming();
        initLayoutTopRate();
        initLayoutGenres();
        loadMovies();
    }

    private void initMoviesAdapters() {
        mPopularMoviesAdapter = new HomeAdapter(this);
        mNowPlayingMoviesAdapter = new HomeAdapter(this);
        mUpcomingMoviesAdapter = new HomeAdapter(this);
        mTopRateMoviesAdapter = new HomeAdapter(this);
        mGenresMoviesAdapter = new HomeAdapter(this);
    }

    private void initLayoutPopular() {
        View include = findViewById(R.id.include_popular);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_popular);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mPopularMoviesAdapter);
    }

    private void initLayoutNowPlaying() {
        View include = findViewById(R.id.include_now_playing);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_now_playing);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mNowPlayingMoviesAdapter);
    }

    private void initLayoutUpcoming() {
        View include = findViewById(R.id.include_upcoming);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_upcoming);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mUpcomingMoviesAdapter);
    }

    private void initLayoutTopRate() {
        View include = findViewById(R.id.include_top_rate);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_top_rate);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mTopRateMoviesAdapter);
    }

    private void initLayoutGenres() {
        View include = findViewById(R.id.include_genres);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_genres);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mGenresMoviesAdapter);
    }

    private void loadMovies() {
        mPresenter.loadPopularMovies();
        mPresenter.loadNowPlayingMovies();
        mPresenter.loadUpcomingMovies();
        mPresenter.loadTopRateMovies();
    }

    @Override
    public void onGetPopularMoviesSuccess(List<Movie> movies) {
        mPopularMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetNowPlayingMoviesSuccess(List<Movie> movies) {
        mNowPlayingMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetUpcomingMoviesSuccess(List<Movie> movies) {
        mUpcomingMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetTopRateMoviesSuccess(List<Movie> movies) {
        mTopRateMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetGenresMoviesSuccess(List<Movie> movies) {
        mGenresMoviesAdapter.updateData(movies);
    }
}
