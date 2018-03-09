package com.training.vungoctuan.moviedb.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.screen.BaseActivity;
import com.training.vungoctuan.moviedb.screen.detail.DetailActivity;
import com.training.vungoctuan.moviedb.util.Constant;

import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class MoviesActivity extends BaseActivity implements MoviesContract.View {
    MoviesContract.Presenter mPresenter;
    private ProgressBar mProgressBar;
    private MoviesAdapter mMoviesAdapter;
    private SearchView mSearchView;

    public static Intent getInstance(Context context, String peopleId,
                                     String peopleName) {
        Intent intent = new Intent(context, MoviesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.BUNDLE_PEOPLE_ID, peopleId);
        intent.putExtra(Constant.BUNDLE_PEOPLE_NAME, peopleName);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        mPresenter = new MoviesPresenter();
        mPresenter.setView(this);
        mMoviesAdapter = new MoviesAdapter(this, new MoviesAdapter.LoadMoviesCallback() {
            @Override
            public void onItemClicked(Movie movie) {
                startActivity(
                    DetailActivity.getInstance(getApplicationContext(), movie));
            }
        });
        initToolbar();
        initLayout();
        initSearchView();
    }

    private void initToolbar() {
        View includeToolbar = findViewById(R.id.toolbar_result);
        Toolbar toolbar = includeToolbar.findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        setTitle(getString(R.string.title_loading));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setTitle(getIntent().getStringExtra(Constant.BUNDLE_PEOPLE_NAME));
    }

    private void initSearchView() {
        View include = findViewById(R.id.toolbar_result);
        mSearchView = include.findViewById(R.id.search_movies);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                submitSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
    }

    private void submitSearch(String query) {
        mSearchView.setQuery("", false);
        mSearchView.setIconified(true);
        mPresenter.loadMovieFromApi(
            Constant.ApiRequestUrl.API_MOVIES_BY_SEARCH,
            query);
        setTitle(String.format(getString(R.string.title_results), query));
        mProgressBar.setVisibility(View.VISIBLE);
        mSearchView.setVisibility(View.GONE);
        mMoviesAdapter.clearData();
    }

    private void initLayout() {
        RecyclerView recyclerMoviesResult = findViewById(R.id.recycler_movies_result);
        recyclerMoviesResult.setAdapter(mMoviesAdapter);
        mProgressBar = findViewById(R.id.progressbar_movies);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetMoviesSuccess(List<Movie> movies) {
        mProgressBar.setVisibility(View.GONE);
        mSearchView.setVisibility(View.VISIBLE);
        mMoviesAdapter.updateData(movies);
    }
}
