package com.training.vungoctuan.moviedb.data.source;

import com.training.vungoctuan.moviedb.data.model.Genre;

import java.util.List;

/**
 * Created by vungoctuan on 3/10/18.
 */
public interface GenreDataSource {
    interface LoadGenresCallback {
        void onGenresLoaded(List<Genre> genres);
        void onDataNotAvailable();
    }

    interface RemoteDataSource {
        void loadGenres(LoadGenresCallback callback);
    }
}
