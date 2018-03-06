package com.training.vungoctuan.moviedb.data.repository;

import com.training.vungoctuan.moviedb.data.source.ProductionDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.ProductionRemoteDataSource;

/**
 * Created by vungoctuan on 3/5/18.
 */
public class ProductionRepository implements ProductionDataSource.RemoteDataSource {
    private static ProductionRepository sInstance;
    private ProductionRemoteDataSource mProductionRemoteDataSource;

    private ProductionRepository(
        ProductionRemoteDataSource productionRemoteDataSource) {
        mProductionRemoteDataSource = productionRemoteDataSource;
    }

    public static ProductionRepository getInstance(
        ProductionRemoteDataSource mProductionRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new ProductionRepository(mProductionRemoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void getProductionByMovieId(String id,
                                       ProductionDataSource.LoadProductionsCallback callback) {
        mProductionRemoteDataSource.getProductionByMovieId(id, callback);
    }
}
