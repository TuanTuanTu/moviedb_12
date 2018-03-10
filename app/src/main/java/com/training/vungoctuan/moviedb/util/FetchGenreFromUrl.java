package com.training.vungoctuan.moviedb.util;

import android.os.AsyncTask;

import com.training.vungoctuan.moviedb.data.model.Genre;
import com.training.vungoctuan.moviedb.data.source.GenreDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by vungoctuan on 3/10/18.
 */
public class FetchGenreFromUrl extends AsyncTask<String, Void, List<Genre>> {
    private GenreDataSource.LoadGenresCallback mCallback;

    public FetchGenreFromUrl(
        GenreDataSource.LoadGenresCallback callback) {
        mCallback = callback;
    }

    @Override
    protected List<Genre> doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                .parseJsonToGenre(RequestAPIUtils.getJsonStringFromUrl(strings[0]));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Genre> genres) {
        if (mCallback == null) return;
        if (genres == null) {
            mCallback.onDataNotAvailable();
        } else {
            mCallback.onGenresLoaded(genres);
        }
    }
}
