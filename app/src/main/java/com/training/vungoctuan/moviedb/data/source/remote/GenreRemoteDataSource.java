package com.training.vungoctuan.moviedb.data.source.remote;

import com.training.vungoctuan.moviedb.data.source.GenreDataSource;
import com.training.vungoctuan.moviedb.util.Constant;
import com.training.vungoctuan.moviedb.util.FetchGenreFromUrl;

/**
 * Created by vungoctuan on 3/10/18.
 */
public class GenreRemoteDataSource implements GenreDataSource.RemoteDataSource {
    private static GenreRemoteDataSource sInstance;

    public static GenreRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new GenreRemoteDataSource();
        }
        return sInstance;
    }

    private GenreRemoteDataSource() {
    }

    @Override
    public void loadGenres(GenreDataSource.LoadGenresCallback callback) {
        new FetchGenreFromUrl(callback).execute(
            Constant.ApiRequestUrl.API_GENRES_REQUEST);
    }
}
