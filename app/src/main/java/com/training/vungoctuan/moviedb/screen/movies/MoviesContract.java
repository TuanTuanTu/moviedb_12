package com.training.vungoctuan.moviedb.screen.movies;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.screen.BasePresenter;

import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public interface MoviesContract {
    interface View {
        void onGetMoviesSuccess(List<Movie> movies);
    }

    interface Presenter extends BasePresenter<View> {
        void loadMovieFromApi(String url, String id);
    }
}
