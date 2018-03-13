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
        void onGetMoviesFailed();
        void onAddFavouriteSuccess(Movie movie);
        void onAddFavouriteFailed();
        void onDeleteFavouriteSuccess(Movie movie);
        void onDeleteFavouriteFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void loadMovieFromApi(String url, String id);
        void getFavouriteMovie();
        void addMovieToFavourite(Movie movie);
        void deleteMovieFromFavourite(Movie movie);
    }
}
