package com.training.vungoctuan.moviedb.data.repository;

import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.MovieRemoteDataSource;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class MovieRepository implements MovieDataSource.RemoteDataSource {
    private static MovieRepository sInstance;
    private MovieRemoteDataSource mMovieRemoteDataSource;

    private MovieRepository(MovieRemoteDataSource movieRemoteDataSource) {
        mMovieRemoteDataSource = movieRemoteDataSource;
    }

    public static MovieRepository getInstance(
        MovieRemoteDataSource movieRemoteDataSource) {
        if (sInstance == null)
            sInstance = new MovieRepository(movieRemoteDataSource);
        return sInstance;
    }

    @Override
    public void getMoviesByCategories(String categories, String language,
                                      String page,
                                      MovieDataSource.LoadMoviesCallback callback) {
        mMovieRemoteDataSource.getMoviesByCategories(
            categories,
            language,
            page,
            callback);
    }
}
