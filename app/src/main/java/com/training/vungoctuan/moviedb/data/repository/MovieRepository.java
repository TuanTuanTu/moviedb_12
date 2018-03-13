package com.training.vungoctuan.moviedb.data.repository;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.data.source.local.MovieLocalDataSource;
import com.training.vungoctuan.moviedb.data.source.local.MoviesDatabaseHelper;
import com.training.vungoctuan.moviedb.data.source.remote.MovieRemoteDataSource;

import java.util.List;

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
    public void addMovieToLocal(Movie movie) throws Exception {
        mMovieLocalDataSource.addMovieToLocal(movie);
    }

    @Override
    public void deleteMovieFromLocal(Movie movie) throws Exception {
        mMovieLocalDataSource.deleteMovieFromLocal(movie);
    }

    @Override
    public void getMoviesFromLocal(List<Movie> movies) throws Exception {
        mMovieLocalDataSource.getMoviesFromLocal(movies);
    }
}
