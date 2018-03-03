package com.training.vungoctuan.moviedb.data.source.remote;

import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.util.Constant;
import com.training.vungoctuan.moviedb.util.FetchDataFromUrl;

/**
 * Created by vungoctuan on 3/2/18.
 */
public class MovieRemoteDataSource implements MovieDataSource.RemoteDataSource {
    private static MovieRemoteDataSource sInstance;

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) sInstance = new MovieRemoteDataSource();
        return sInstance;
    }

    private MovieRemoteDataSource() {
    }

    @Override
    public void getPopularMovies(String language, String page,
                                 MovieDataSource.LoadMoviesCallback callback) {
        new FetchDataFromUrl(callback)
            .execute(
                String.format(
                    Constant.API_URL_REQUEST,
                    Constant.API_URL_MOVIE_POPULAR,
                    language,
                    page));
    }

    @Override
    public void getNowPlayingMovies(String language, String page,
                                    MovieDataSource.LoadMoviesCallback callback) {
        new FetchDataFromUrl(callback)
            .execute(
                String.format(
                    Constant.API_URL_REQUEST,
                    Constant.API_URL_MOVIE_NOW_PLAYING,
                    language,
                    page));
    }
}
