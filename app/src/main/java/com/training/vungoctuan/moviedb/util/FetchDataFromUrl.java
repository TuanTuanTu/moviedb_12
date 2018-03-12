package com.training.vungoctuan.moviedb.util;

import android.os.AsyncTask;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;

import org.json.JSONException;

import java.io.IOException;
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
            return RequestAPIUtils
                .parseJsonToMovies(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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

