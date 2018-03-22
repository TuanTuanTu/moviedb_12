package com.training.vungoctuan.moviedb.screen.home;

import com.training.vungoctuan.moviedb.data.model.Genre;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.screen.BasePresenter;

import java.util.List;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class HomeContract {
    public interface View {
        void onGetPopularMoviesSuccess(List<Movie> movies);
        void onGetNowPlayingMoviesSuccess(List<Movie> movies);
        void onGetUpcomingMoviesSuccess(List<Movie> movies);
        void onGetTopRateMoviesSuccess(List<Movie> movies);
        void onGetGenresSuccess(List<Genre> genres);
        void onGetPopularMoviesFailed();
        void onGetNowPlayingMoviesFailed();
        void onGetUpcomingMoviesFailed();
        void onGetTopRateMoviesFailed();
        void onGetGenresMoviesFailed();
    }

    public interface Presenter extends BasePresenter<View> {
        void loadPopularMovies();
        void loadNowPlayingMovies();
        void loadUpcomingMovies();
        void loadTopRateMovies();
        void loadGenresMovies();
        boolean loadAfterNetworkChange();
    }
}
