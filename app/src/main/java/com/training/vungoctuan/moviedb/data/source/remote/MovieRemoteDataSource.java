package com.training.vungoctuan.moviedb.data.source.remote;

import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.util.Constant;
import com.training.vungoctuan.moviedb.util.FetchDataFromUrl;

/**
 * Created by vungoctuan on 3/2/18.
 */
public class MovieRemoteDataSource implements MovieDataSource.RemoteDataSource {
    private static MovieRemoteDataSource sInstance;

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) sInstance = new MovieRemoteDataSource();
        return sInstance;
    }

    private MovieRemoteDataSource() {
    }

    @Override
    public void getMoviesByCategories(String categories, String language, String page,
                                      MovieDataSource.LoadMoviesCallback callback) {
        new FetchDataFromUrl(callback)
            .execute(
                String.format(
                    Constant.ApiRequestUrl.API_URL_REQUEST,
                    categories,
                    language,
                    page));
    }
}
