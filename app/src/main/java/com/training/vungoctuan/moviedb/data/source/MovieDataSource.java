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

    interface LocalDataSource {
        void addMovieToLocal(Movie movie) throws Exception;
        void deleteMovieFromLocal(Movie movie) throws Exception;
        List<Movie> getMoviesFromLocal() throws Exception;
        boolean isFavouriteMovie(String movieId) throws Exception;
    }

    interface RemoteDataSource {
        void getMoviesByCategories(String categories, String language, int page,
                                   LoadMoviesCallback callback);
        void getMoviesByUrl(String id, String url, LoadMoviesCallback callback);
    }
}
