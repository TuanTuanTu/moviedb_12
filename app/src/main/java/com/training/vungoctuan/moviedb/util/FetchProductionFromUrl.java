package com.training.vungoctuan.moviedb.util;

import android.os.AsyncTask;

import com.training.vungoctuan.moviedb.data.model.Production;
import com.training.vungoctuan.moviedb.data.source.ProductionDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by vungoctuan on 3/5/18.
 */
public class FetchProductionFromUrl extends AsyncTask<String, Void, List<Production>> {
    private ProductionDataSource.LoadProductionsCallback mCallback;

    public FetchProductionFromUrl(
        ProductionDataSource.LoadProductionsCallback callback) {
        mCallback = callback;
    }

    @Override
    protected List<Production> doInBackground(String... strings) {
        try {
            return RequestAPIUtils
                .parseJsonToProductions(RequestAPIUtils.getJsonStringFromUrl(strings[0])
                );
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Production> productions) {
        if (mCallback == null) return;
        if (productions == null) {
            mCallback.onDataNotAvailable();
        } else {
            mCallback.onProductionsLoaded(productions);
        }
    }
}
