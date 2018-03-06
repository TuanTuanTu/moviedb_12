package com.training.vungoctuan.moviedb.data.source;

import com.training.vungoctuan.moviedb.data.model.credit.Credit;

/**
 * Created by vungoctuan on 3/5/18.
 */
public interface CreditDataSource {
    interface LoadProductionsCallback {
        void onCreditLoaded(Credit credits);
        void onDataNotAvailable();
    }

    interface RemoteDataSource {
        void getCreditByMovieId(String movieId, CreditDataSource.LoadProductionsCallback callback);
    }
}
