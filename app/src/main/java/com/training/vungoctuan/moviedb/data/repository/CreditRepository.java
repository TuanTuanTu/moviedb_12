package com.training.vungoctuan.moviedb.data.repository;

import com.training.vungoctuan.moviedb.data.source.CreditDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.CreditRemoteDataSource;

/**
 * Created by vungoctuan on 3/5/18.
 */
public class CreditRepository implements CreditDataSource.RemoteDataSource {
    private static CreditRepository sInstance;
    private CreditRemoteDataSource mCreditRemoteDataSource;

    public static CreditRepository getInstance(
        CreditRemoteDataSource creditRemoteDataSource) {
        if (sInstance == null)
            sInstance = new CreditRepository(creditRemoteDataSource);
        return sInstance;
    }

    private CreditRepository(
        CreditRemoteDataSource creditRemoteDataSource) {
        mCreditRemoteDataSource = creditRemoteDataSource;
    }

    @Override
    public void getCreditByMovieId(String movieId,
                                   CreditDataSource.LoadProductionsCallback callback) {
        mCreditRemoteDataSource.getCreditByMovieId(movieId, callback);
    }
}
