package com.training.vungoctuan.moviedb.data.source.remote;

import com.training.vungoctuan.moviedb.data.source.ProductionDataSource;
import com.training.vungoctuan.moviedb.util.Constant;
import com.training.vungoctuan.moviedb.util.FetchProductionFromUrl;

/**
 * Created by vungoctuan on 3/5/18.
 */
public class ProductionRemoteDataSource implements ProductionDataSource.RemoteDataSource {
    private static ProductionRemoteDataSource sInstance;

    public static ProductionRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new ProductionRemoteDataSource();
        }
        return sInstance;
    }

    private ProductionRemoteDataSource() {
    }

    @Override
    public void getProductionByMovieId(String id,
                                       ProductionDataSource.LoadProductionsCallback callback) {
        new FetchProductionFromUrl(callback).execute(
            String.format(
                Constant.ApiRequestUrl.API_PRODUCTION_BY_ID_REQUEST, id)
        );
    }
}
