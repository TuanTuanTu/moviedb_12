package com.training.vungoctuan.moviedb.data.source;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.util.localtask.TaskAddFavourite;
import com.training.vungoctuan.moviedb.util.localtask.TaskCheckFavourite;
import com.training.vungoctuan.moviedb.util.localtask.TaskDeleteFavourite;

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
        void addMovieToLocal(Movie movie, TaskAddFavourite.AddFavouriteCallback callback);
        void deleteMovieFromLocal(Movie movie,
                                  TaskDeleteFavourite.DeleteFavouriteCallback callback);
        void getMoviesFromLocal(LoadMoviesCallback callback);
        void checkFavouriteMovie(Movie movie, TaskCheckFavourite.Callback callback);
    }

    interface RemoteDataSource {
        void getMoviesByCategories(String categories, String language, int page,
                                   LoadMoviesCallback callback);
        void getMoviesByUrl(String id, String url, LoadMoviesCallback callback);
    }
}
