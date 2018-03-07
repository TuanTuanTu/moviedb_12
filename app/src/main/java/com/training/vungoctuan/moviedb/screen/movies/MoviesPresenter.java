package com.training.vungoctuan.moviedb.screen.movies;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.repository.MovieRepository;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.MovieRemoteDataSource;

import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class MoviesPresenter implements MoviesContract.Presenter {
    private MoviesContract.View mView;
    private MovieRepository mMovieRepository;

    MoviesPresenter() {
        mMovieRepository =
            MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
    }

    @Override
    public void setView(MoviesContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadMovieFromApi(String url, String id) {
        mMovieRepository.getMoviesByUrl(id, url, new MovieDataSource.LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                mView.onGetMoviesSuccess(movies);
            }

            // TODO: 3/7/18 Show textview to UI alert "No data" 
            @Override
            public void onDataNotAvailable() {
            }
        });
    }
}
