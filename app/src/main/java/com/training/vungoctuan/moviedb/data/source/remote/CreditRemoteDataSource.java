package com.training.vungoctuan.moviedb.data.source.remote;

import com.training.vungoctuan.moviedb.data.source.CreditDataSource;
import com.training.vungoctuan.moviedb.util.Constant;
import com.training.vungoctuan.moviedb.util.FetchCreditFromUrl;

/**
 * Created by vungoctuan on 3/5/18.
 */
public class CreditRemoteDataSource implements CreditDataSource.RemoteDataSource {
    private static CreditRemoteDataSource sInstance;

    public static CreditRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new CreditRemoteDataSource();
        }
        return sInstance;
    }

    private CreditRemoteDataSource() {
    }

    @Override
    public void getCreditByMovieId(String movieId,
                                   CreditDataSource.LoadProductionsCallback callback) {
        new FetchCreditFromUrl(callback).execute(
            String.format(Constant.ApiRequestUrl.API_CREDIT_BY_MOVIE_REQUEST, movieId)
        );
    }
}
