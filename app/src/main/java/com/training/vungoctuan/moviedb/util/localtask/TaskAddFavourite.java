package com.training.vungoctuan.moviedb.util.localtask;

import android.os.AsyncTask;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.source.local.MoviesDatabaseHelper;

/**
 * Created by vungoctuan on 3/14/18.
 */
public class TaskAddFavourite extends AsyncTask<Movie, Void, Boolean> {
    private AddFavouriteCallback mCallback;
    private MoviesDatabaseHelper mDatabase;

    public TaskAddFavourite(
        AddFavouriteCallback callback,
        MoviesDatabaseHelper database) {
        mCallback = callback;
        mDatabase = database;
    }

    @Override
    protected Boolean doInBackground(Movie... movies) {
        try {
            mDatabase.addMovies(movies[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean isSuccess) {
        if (isSuccess) {
            mCallback.onAddSuccess();
        } else {
            mCallback.onAddFailed();
        }
    }

    public interface AddFavouriteCallback {
        void onAddSuccess();
        void onAddFailed();
    }
}
