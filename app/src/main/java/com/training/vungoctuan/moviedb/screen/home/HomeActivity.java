package com.training.vungoctuan.moviedb.screen.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.screen.BaseActivity;
import com.training.vungoctuan.moviedb.screen.detail.DetailActivity;
import com.training.vungoctuan.moviedb.screen.movies.MoviesBySearchActivity;

import java.util.List;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class HomeActivity extends BaseActivity implements HomeContract.View {
    private HomeContract.Presenter mPresenter;
    private HomeAdapter mPopularMoviesAdapter, mNowPlayingMoviesAdapter,
        mUpcomingMoviesAdapter, mTopRateMoviesAdapter, mGenresMoviesAdapter;
    private ProgressBar mProgressBarPopular, mProgressBarNowPlaying,
        mProgressBarUpcoming, mProgressBarTopRate, mProgressBarGenres;

    public static Intent getInstance(Context context) {
        return new Intent(context, HomeActivity.class);
    }

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
        initSearchView();
        loadMovies();
    }

    private void initMoviesAdapters() {
        HomeAdapter.LoadAdapterDataCallback callback =
            new HomeAdapter.LoadAdapterDataCallback() {
                @Override
                public void onItemClick(final Movie movie) {
                    startActivity(
                        DetailActivity.getInstance(getApplicationContext(), movie));
                }
            };
        mPopularMoviesAdapter = new HomeAdapter(this, callback);
        mNowPlayingMoviesAdapter = new HomeAdapter(this, callback);
        mUpcomingMoviesAdapter = new HomeAdapter(this, callback);
        mTopRateMoviesAdapter = new HomeAdapter(this, callback);
        mGenresMoviesAdapter = new HomeAdapter(this, callback);
    }

    private void initLayoutPopular() {
        View include = findViewById(R.id.include_popular);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_popular);
        mProgressBarPopular = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mPopularMoviesAdapter);
    }

    private void initLayoutNowPlaying() {
        View include = findViewById(R.id.include_now_playing);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_now_playing);
        mProgressBarNowPlaying = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mNowPlayingMoviesAdapter);
    }

    private void initLayoutUpcoming() {
        View include = findViewById(R.id.include_upcoming);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_upcoming);
        mProgressBarUpcoming = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mUpcomingMoviesAdapter);
    }

    private void initLayoutTopRate() {
        View include = findViewById(R.id.include_top_rate);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_top_rate);
        mProgressBarTopRate = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mTopRateMoviesAdapter);
    }

    private void initLayoutGenres() {
        View include = findViewById(R.id.include_genres);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_genres);
        mProgressBarGenres = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mGenresMoviesAdapter);
    }

    private void initSearchView() {
        View include = findViewById(R.id.toolbar);
        final SearchView searchView = include.findViewById(R.id.search_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.setQuery("", false);
                searchView.setIconified(true);
                startActivity(MoviesBySearchActivity
                    .getInstance(getApplicationContext(), query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
    }

    private void loadMovies() {
        mPresenter.loadPopularMovies();
        mPresenter.loadNowPlayingMovies();
        mPresenter.loadUpcomingMovies();
        mPresenter.loadTopRateMovies();
        mProgressBarPopular.setVisibility(View.VISIBLE);
        mProgressBarNowPlaying.setVisibility(View.VISIBLE);
        mProgressBarTopRate.setVisibility(View.VISIBLE);
        mProgressBarUpcoming.setVisibility(View.VISIBLE);
        mProgressBarGenres.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetPopularMoviesSuccess(List<Movie> movies) {
        mProgressBarPopular.setVisibility(View.GONE);
        mPopularMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetNowPlayingMoviesSuccess(List<Movie> movies) {
        mProgressBarNowPlaying.setVisibility(View.GONE);
        mNowPlayingMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetUpcomingMoviesSuccess(List<Movie> movies) {
        mProgressBarUpcoming.setVisibility(View.GONE);
        mUpcomingMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetTopRateMoviesSuccess(List<Movie> movies) {
        mProgressBarTopRate.setVisibility(View.GONE);
        mTopRateMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetGenresMoviesSuccess(List<Movie> movies) {
        mProgressBarGenres.setVisibility(View.GONE);
        mGenresMoviesAdapter.updateData(movies);
    }
}
