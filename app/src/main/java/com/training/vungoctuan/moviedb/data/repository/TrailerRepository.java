package com.training.vungoctuan.moviedb.data.repository;

import com.training.vungoctuan.moviedb.data.source.TrailerDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.TrailerRemoteDataSource;

/**
 * Created by vungoctuan on 3/9/18.
 */
public class TrailerRepository implements TrailerDataSource.RemoteDataSource {
    private TrailerRemoteDataSource mRemoteDataSource;
    private static TrailerRepository sInstance;

    public static TrailerRepository getInstance(
        TrailerRemoteDataSource trailerRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new TrailerRepository(trailerRemoteDataSource);
        }
        return sInstance;
    }

    private TrailerRepository(
        TrailerRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void getTrailerByMovieId(String id,
                                    TrailerDataSource.LoadTrailersCallback loadTrailersCallback) {
        mRemoteDataSource.getTrailerByMovieId(id, loadTrailersCallback);
    }
}
