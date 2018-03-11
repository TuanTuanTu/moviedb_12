package com.training.vungoctuan.moviedb.data.repository;

import com.training.vungoctuan.moviedb.data.source.GenreDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.GenreRemoteDataSource;

/**
 * Created by vungoctuan on 3/10/18.
 */
public class GenreRepository implements GenreDataSource.RemoteDataSource {
    private GenreRemoteDataSource mGenreRemoteDataSource;
    private static GenreRepository sInstance;

    public static GenreRepository getInstance(GenreRemoteDataSource genreRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new GenreRepository(genreRemoteDataSource);
        }
        return sInstance;
    }

    private GenreRepository(
        GenreRemoteDataSource genreRemoteDataSource) {
        mGenreRemoteDataSource = genreRemoteDataSource;
    }

    @Override
    public void loadGenres(GenreDataSource.LoadGenresCallback callback) {
        mGenreRemoteDataSource.loadGenres(callback);
    }
}
