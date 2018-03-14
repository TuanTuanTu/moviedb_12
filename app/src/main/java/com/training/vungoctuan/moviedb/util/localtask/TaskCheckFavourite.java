package com.training.vungoctuan.moviedb.util.localtask;

import android.os.AsyncTask;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.source.local.MoviesDatabaseHelper;

/**
 * Created by vungoctuan on 3/14/18.
 */
public class TaskCheckFavourite extends AsyncTask<Movie, Void, Movie> {
    private Callback mCallback;

    public TaskCheckFavourite(
        Callback callback) {
        mCallback = callback;
    }

    @Override
    protected Movie doInBackground(Movie... movies) {
        try {
            if (MoviesDatabaseHelper.getInstance().checkExistMovie(movies[0].getId())) {
                return movies[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Movie movie) {
        if (mCallback == null) return;
        if (movie == null) {
            mCallback.moviesNotExisting();
        } else {
            movie.setFavourite(true);
            mCallback.moviesExisting(movie);
        }
    }

    public interface Callback {
        void moviesExisting(Movie movie);
        void moviesNotExisting();
    }
}
