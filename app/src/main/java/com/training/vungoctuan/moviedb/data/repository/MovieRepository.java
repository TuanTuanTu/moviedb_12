package com.training.vungoctuan.moviedb.data.repository;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.data.source.local.MovieLocalDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.MovieRemoteDataSource;
import com.training.vungoctuan.moviedb.util.localtask.TaskAddFavourite;
import com.training.vungoctuan.moviedb.util.localtask.TaskCheckFavourite;
import com.training.vungoctuan.moviedb.util.localtask.TaskDeleteFavourite;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class MovieRepository implements MovieDataSource.RemoteDataSource,
    MovieDataSource.LocalDataSource {
    private static MovieRepository sInstance;
    private MovieRemoteDataSource mMovieRemoteDataSource;
    private MovieLocalDataSource mMovieLocalDataSource;

    private MovieRepository(MovieRemoteDataSource movieRemoteDataSource,
                            MovieLocalDataSource movieLocalDataSource) {
        mMovieRemoteDataSource = movieRemoteDataSource;
        mMovieLocalDataSource = movieLocalDataSource;
    }

    public static MovieRepository getInstance(
        MovieRemoteDataSource movieRemoteDataSource,
        MovieLocalDataSource movieLocalDataSource) {
        if (sInstance == null)
            sInstance = new MovieRepository(movieRemoteDataSource, movieLocalDataSource);
        return sInstance;
    }

    @Override
    public void getMoviesByCategories(String categories, String language,
                                      int page,
                                      MovieDataSource.LoadMoviesCallback callback) {
        mMovieRemoteDataSource.getMoviesByCategories(
            categories,
            language,
            page,
            callback);
    }

    @Override
    public void getMoviesByUrl(String id, String url,
                               MovieDataSource.LoadMoviesCallback callback) {
        mMovieRemoteDataSource.getMoviesByUrl(id, url, callback);
    }

    @Override
    public void addMovieToLocal(Movie movie, TaskAddFavourite.AddFavouriteCallback callback) {
        mMovieLocalDataSource.addMovieToLocal(movie, callback);
    }

    @Override
    public void deleteMovieFromLocal(Movie movie, TaskDeleteFavourite.DeleteFavouriteCallback
        callback) {
        mMovieLocalDataSource.deleteMovieFromLocal(movie, callback);
    }

    @Override
    public void getMoviesFromLocal(MovieDataSource.LoadMoviesCallback callback) {
        mMovieLocalDataSource.getMoviesFromLocal(callback);
    }

    @Override
    public void checkFavouriteMovie(Movie movie, TaskCheckFavourite.Callback callback) {
        mMovieLocalDataSource.checkFavouriteMovie(movie, callback);
    }
}
