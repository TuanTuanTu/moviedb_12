package com.training.vungoctuan.moviedb.util.localtask;

import android.os.AsyncTask;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.data.source.local.MoviesDatabaseHelper;

import java.util.List;

/**
 * Created by vungoctuan on 3/14/18.
 */
public class FetchFavouriteMovies extends AsyncTask<Void, Void, List<Movie>> {
    private MovieDataSource.LoadMoviesCallback mCallback;

    public FetchFavouriteMovies(
        MovieDataSource.LoadMoviesCallback callback) {
        mCallback = callback;
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        List<Movie> movies = MoviesDatabaseHelper.getInstance().getAllMovies();
        for (int i = 0; i < movies.size(); i++) {
            try {
                if(MoviesDatabaseHelper.getInstance().checkExistMovie(movies.get(i).getId())){
                   movies.get(i).setFavourite(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return movies;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if (mCallback == null) return;
        if (movies == null || movies.size() == 0) {
            mCallback.onDataNotAvailable();
        } else {
            mCallback.onMoviesLoaded(movies);
        }
    }
}
