package com.training.vungoctuan.moviedb.screen.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.data.model.Genre;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.source.local.MoviesDatabaseHelper;
import com.training.vungoctuan.moviedb.screen.BaseActivity;
import com.training.vungoctuan.moviedb.screen.detail.DetailActivity;
import com.training.vungoctuan.moviedb.screen.movies.MoviesByFavourite;
import com.training.vungoctuan.moviedb.screen.movies.MoviesByGenreActivity;
import com.training.vungoctuan.moviedb.screen.movies.MoviesBySearchActivity;
import com.training.vungoctuan.moviedb.util.EndlessRecyclerOnScrollListener;
import com.training.vungoctuan.moviedb.util.NetworkReceiver;

import java.util.List;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class HomeActivity extends BaseActivity implements HomeContract.View {
    private HomeContract.Presenter mPresenter;
    private HomeAdapter mPopularMoviesAdapter, mNowPlayingMoviesAdapter,
        mUpcomingMoviesAdapter, mTopRateMoviesAdapter;
    private HomeGenresAdapter mHomeGenresAdapter;
    private ProgressBar mProgressBarPopular, mProgressBarNowPlaying,
        mProgressBarUpcoming, mProgressBarTopRate, mProgressBarGenres;
    private EndlessRecyclerOnScrollListener mPopularOnScrollListener,
        mNowPlayingOnScrollListener, mUpcomingOnScrollListener, mTopRateOnScrollListener;

    public static Intent getInstance(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MoviesDatabaseHelper.initialize(this);
        mPresenter = new HomePresenter(getMovieRepository());
        mPresenter.setView(this);
        initMoviesAdapters();
        initLayoutPopular();
        initLayoutNowPlaying();
        initLayoutUpcoming();
        initLayoutTopRate();
        initLayoutGenres();
        initToolbar();
        initNetworkBroadcast();
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
        mHomeGenresAdapter = new HomeGenresAdapter(this,
            new HomeGenresAdapter.LoadGenresAdapterCallback() {
                @Override
                public void onItemClick(Genre genre) {
                    startActivity(
                        MoviesByGenreActivity.getInstance(getApplicationContext(), genre));
                }
            });
    }

    private void initLayoutPopular() {
        View include = findViewById(R.id.include_popular);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_popular);
        mProgressBarPopular = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mPopularMoviesAdapter);
        mPopularOnScrollListener = new EndlessRecyclerOnScrollListener(
            new EndlessRecyclerOnScrollListener.LoadMoreMovies() {
                @Override
                public void loadMoreMovies() {
                    mPresenter.loadPopularMovies();
                    mProgressBarPopular.setVisibility(View.VISIBLE);
                }
            });
        recyclerView.addOnScrollListener(mPopularOnScrollListener);
    }

    private void initLayoutNowPlaying() {
        View include = findViewById(R.id.include_now_playing);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_now_playing);
        mProgressBarNowPlaying = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mNowPlayingMoviesAdapter);
        mNowPlayingOnScrollListener = new EndlessRecyclerOnScrollListener(
            new EndlessRecyclerOnScrollListener.LoadMoreMovies() {
                @Override
                public void loadMoreMovies() {
                    mPresenter.loadNowPlayingMovies();
                    mProgressBarNowPlaying.setVisibility(View.VISIBLE);
                }
            });
        recyclerView.addOnScrollListener(mNowPlayingOnScrollListener);
    }

    private void initLayoutUpcoming() {
        View include = findViewById(R.id.include_upcoming);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_upcoming);
        mProgressBarUpcoming = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mUpcomingMoviesAdapter);
        mUpcomingOnScrollListener = new EndlessRecyclerOnScrollListener(
            new EndlessRecyclerOnScrollListener.LoadMoreMovies() {
                @Override
                public void loadMoreMovies() {
                    mPresenter.loadUpcomingMovies();
                    mProgressBarUpcoming.setVisibility(View.VISIBLE);
                }
            });
        recyclerView.addOnScrollListener(mUpcomingOnScrollListener);
    }

    private void initLayoutTopRate() {
        View include = findViewById(R.id.include_top_rate);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_top_rate);
        mProgressBarTopRate = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mTopRateMoviesAdapter);
        mTopRateOnScrollListener = new EndlessRecyclerOnScrollListener(
            new EndlessRecyclerOnScrollListener.LoadMoreMovies() {
                @Override
                public void loadMoreMovies() {
                    mPresenter.loadTopRateMovies();
                    mProgressBarTopRate.setVisibility(View.VISIBLE);
                }
            });
        recyclerView.addOnScrollListener(mTopRateOnScrollListener);
    }

    private void initLayoutGenres() {
        View include = findViewById(R.id.include_genres);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_genres);
        mProgressBarGenres = include.findViewById(R.id.progressbar_recycler);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mHomeGenresAdapter);
    }

    private void initToolbar() {
        View include = findViewById(R.id.toolbar_home);
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
        Button button = include.findViewById(R.id.button_favourite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MoviesByFavourite.getInstance(getApplicationContext()));
            }
        });
    }

    private void loadMovies() {
        mProgressBarPopular.setVisibility(View.VISIBLE);
        mProgressBarNowPlaying.setVisibility(View.VISIBLE);
        mProgressBarTopRate.setVisibility(View.VISIBLE);
        mProgressBarUpcoming.setVisibility(View.VISIBLE);
        mProgressBarGenres.setVisibility(View.VISIBLE);
    }

    private void initNetworkBroadcast() {
        initNetworkBroadcastReceiver(new NetworkReceiver.NetworkStateCallback() {
            @Override
            public void onNetworkConnected() {
                if (mPresenter.loadAfterNetworkChange()) {
                    loadMovies();
                }
            }

            @Override
            public void onNetworkDisconnected() {
                Snackbar.make(findViewById(R.id.home_parent), R.string.home_check_network,
                    Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onGetPopularMoviesSuccess(List<Movie> movies) {
        mPopularOnScrollListener.setLoadingStatus(false);
        mProgressBarPopular.setVisibility(View.GONE);
        mPopularMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetNowPlayingMoviesSuccess(List<Movie> movies) {
        mNowPlayingOnScrollListener.setLoadingStatus(false);
        mProgressBarNowPlaying.setVisibility(View.GONE);
        mNowPlayingMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetUpcomingMoviesSuccess(List<Movie> movies) {
        mUpcomingOnScrollListener.setLoadingStatus(false);
        mProgressBarUpcoming.setVisibility(View.GONE);
        mUpcomingMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetTopRateMoviesSuccess(List<Movie> movies) {
        mTopRateOnScrollListener.setLoadingStatus(false);
        mProgressBarTopRate.setVisibility(View.GONE);
        mTopRateMoviesAdapter.updateData(movies);
    }

    @Override
    public void onGetGenresSuccess(List<Genre> genres) {
        mProgressBarGenres.setVisibility(View.GONE);
        mHomeGenresAdapter.updateData(genres);
    }

    @Override
    public void onGetPopularMoviesFailed() {
        mPopularOnScrollListener.setLoadingStatus(false);
    }

    @Override
    public void onGetNowPlayingMoviesFailed() {
        mNowPlayingOnScrollListener.setLoadingStatus(false);
    }

    @Override
    public void onGetUpcomingMoviesFailed() {
        mUpcomingOnScrollListener.setLoadingStatus(false);
    }

    @Override
    public void onGetTopRateMoviesFailed() {
        mTopRateOnScrollListener.setLoadingStatus(false);
    }

    @Override
    public void onGetGenresMoviesFailed() {
        Toast.makeText(this, R.string.home_load_genres_failed, Toast.LENGTH_SHORT).show();
    }
}
