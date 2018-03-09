package com.training.vungoctuan.moviedb.data.source;

import com.training.vungoctuan.moviedb.data.model.Trailer;

import java.util.List;

/**
 * Created by vungoctuan on 3/9/18.
 */
public interface TrailerDataSource {
    interface LoadTrailersCallback {
        void onTrailersLoaded(List<Trailer> trailers);
        void onDataNotAvailable();
    }

    interface RemoteDataSource {
        void getTrailerByMovieId(String id, LoadTrailersCallback loadTrailersCallback);
    }
}
