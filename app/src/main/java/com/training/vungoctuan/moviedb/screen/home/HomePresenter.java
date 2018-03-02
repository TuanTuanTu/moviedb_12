package com.training.vungoctuan.moviedb.screen.home;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.repository.MovieRepository;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.MovieRemoteDataSource;
import com.training.vungoctuan.moviedb.util.Constant;

import java.util.List;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private MovieRepository mMovieRepository;

    HomePresenter() {
        mMovieRepository =
            MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
    }

    @Override
    public void setView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadPopularMovies() {
        mMovieRepository.getPopularMovies(Constant.API_URL_LANGUAGE,
            Constant.API_URL_FIRST_PAGE,
            new MovieDataSource
                .LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mView.onGetPopularMoviesSuccess(movies);
                }

                @Override
                public void onDataNotAvailable() {
                }
            });
    }

    @Override
    public void loadNowPlayingMovies() {
        mMovieRepository.getNowPlayingMovies(Constant.API_URL_LANGUAGE,
            Constant.API_URL_FIRST_PAGE,
            new MovieDataSource.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mView.onGetNowPlayingMoviesSuccess(movies);
                }

                @Override
                public void onDataNotAvailable() {
                }
            });
    }
}
