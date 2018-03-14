package com.training.vungoctuan.moviedb.util.localtask;

import android.os.AsyncTask;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.source.local.MoviesDatabaseHelper;

/**
 * Created by vungoctuan on 3/14/18.
 */
public class TaskDeleteFavourite extends AsyncTask<Movie, Void, Boolean> {
    private DeleteFavouriteCallback mCallback;

    public TaskDeleteFavourite(
        DeleteFavouriteCallback callback) {
        mCallback = callback;
    }

    @Override
    protected Boolean doInBackground(Movie... movies) {
        try {
            MoviesDatabaseHelper.getInstance().deleteMovies(movies[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean isSuccess) {
        if (isSuccess){
            mCallback.onDeleteSuccess();
        }else {
            mCallback.onDeleteFailed();
        }
    }

    public interface DeleteFavouriteCallback {
        void onDeleteSuccess();
        void onDeleteFailed();
    }
}
