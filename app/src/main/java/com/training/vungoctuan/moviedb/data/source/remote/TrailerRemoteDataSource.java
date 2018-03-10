package com.training.vungoctuan.moviedb.data.source.remote;

import com.training.vungoctuan.moviedb.data.source.TrailerDataSource;
import com.training.vungoctuan.moviedb.util.Constant;
import com.training.vungoctuan.moviedb.util.FetchTrailerFromUrl;

/**
 * Created by vungoctuan on 3/9/18.
 */
public class TrailerRemoteDataSource implements TrailerDataSource.RemoteDataSource {
    private static TrailerRemoteDataSource sInstance;

    public static TrailerRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new TrailerRemoteDataSource();
        }
        return sInstance;
    }

    private TrailerRemoteDataSource() {
    }

    @Override
    public void getTrailerByMovieId(String id,
                                    TrailerDataSource.LoadTrailersCallback callback) {
        new FetchTrailerFromUrl(callback).execute(
            String.format(Constant.ApiRequestUrl.API_TRAILER_BY_MOVIE_ID, id));
    }
}
