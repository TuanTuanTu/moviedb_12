package com.training.vungoctuan.moviedb.data.source;

import com.training.vungoctuan.moviedb.data.model.Production;

import java.util.List;

/**
 * Created by vungoctuan on 3/5/18.
 */
public interface ProductionDataSource {
    interface LoadProductionsCallback {
        void onProductionsLoaded(List<Production> productions);
        void onDataNotAvailable();
    }

    interface RemoteDataSource {
        void getProductionByMovieId(String id, LoadProductionsCallback callback);
    }
}
