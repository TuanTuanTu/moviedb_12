package com.training.vungoctuan.moviedb.data.source.local;

import android.os.AsyncTask;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.util.localtask.FetchFavouriteMovies;
import com.training.vungoctuan.moviedb.util.localtask.TaskAddFavourite;
import com.training.vungoctuan.moviedb.util.localtask.TaskCheckFavourite;
import com.training.vungoctuan.moviedb.util.localtask.TaskDeleteFavourite;

/**
 * Created by vungoctuan on 3/13/18.
 */
public class MovieLocalDataSource implements MovieDataSource.LocalDataSource {
    private static MovieLocalDataSource sInstance;
    private MoviesDatabaseHelper mDatabase;

    private MovieLocalDataSource() {
        mDatabase = MoviesDatabaseHelper.getInstance();
    }

    public static MovieLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new MovieLocalDataSource();
        }
        return sInstance;
    }

    @Override
    public void addMovieToLocal(Movie movie,
                                TaskAddFavourite.AddFavouriteCallback callback) {
        new TaskAddFavourite(callback, mDatabase).executeOnExecutor(AsyncTask
            .THREAD_POOL_EXECUTOR, movie);
    }

    @Override
    public void deleteMovieFromLocal(Movie movie,
                                     TaskDeleteFavourite.DeleteFavouriteCallback callback) {
        new TaskDeleteFavourite(callback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, movie);
    }

    @Override
    public void getMoviesFromLocal(MovieDataSource.LoadMoviesCallback callback) {
        new FetchFavouriteMovies(callback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void checkFavouriteMovie(Movie movie, TaskCheckFavourite.Callback callback) {
        new TaskCheckFavourite(callback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, movie);
    }
}
