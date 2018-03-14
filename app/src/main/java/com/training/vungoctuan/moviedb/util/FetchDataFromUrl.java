package com.training.vungoctuan.moviedb.util;

import android.os.AsyncTask;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.data.source.local.MoviesDatabaseHelper;

import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class FetchDataFromUrl extends AsyncTask<String, Void, List<Movie>> {
    private MovieDataSource.LoadMoviesCallback mCallback;

    public FetchDataFromUrl(MovieDataSource.LoadMoviesCallback callback) {
        mCallback = callback;
    }

    @Override
    protected List<Movie> doInBackground(String... strings) {
        try {
            List<Movie> movies = RequestAPIUtils
                .parseJsonToMovies(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
            for (int i = 0; i < movies.size(); i++) {
                if (MoviesDatabaseHelper.getInstance().checkExistMovie(movies.get(i).getId())) {
                    movies.get(i).setFavourite(true);
                }
            }
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

