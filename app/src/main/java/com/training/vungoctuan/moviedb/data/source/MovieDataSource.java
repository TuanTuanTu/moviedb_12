package com.training.vungoctuan.moviedb.data.source;

import com.training.vungoctuan.moviedb.data.model.Movie;

import java.util.List;

/**
 * Created by vungoctuan on 2/28/18.
 */
public interface MovieDataSource {
    interface LoadMoviesCallback {
        void onMoviesLoaded(List<Movie> movies);
        void onDataNotAvailable();
    }

    interface RemoteDataSource {
        void getMoviesByCategories(String categories, String language, String page,
                                   LoadMoviesCallback callback);
    }
}
