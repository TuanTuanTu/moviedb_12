package com.training.vungoctuan.moviedb.data.source.remote;

import com.training.vungoctuan.moviedb.data.model.api.MovieApi;
import com.training.vungoctuan.moviedb.data.model.api.MovieResults;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.screen.BaseActivity;
import com.training.vungoctuan.moviedb.util.FetchDataFromUrl;

import io.reactivex.Observable;

/**
 * Created by vungoctuan on 3/2/18.
 */
public class MovieRemoteDataSource implements MovieDataSource.RemoteDataSource {
    private static MovieRemoteDataSource sInstance;
    private static MovieApi mMovieApi;

    public static MovieRemoteDataSource getInstance() {
        if (sInstance == null) sInstance = new MovieRemoteDataSource();
        return sInstance;
    }

    private MovieRemoteDataSource() {
        mMovieApi = BaseActivity.getRetrofitInstance().create(MovieApi.class);
    }

    @Override
    public Observable<MovieResults> getMoviesByCategories(String categories, int page) {
        return mMovieApi.getPopularMovies(categories,page);
    }

    @Override
    public void getMoviesByUrl(String id, String url,
                               MovieDataSource.LoadMoviesCallback callback) {
        new FetchDataFromUrl(callback)
            .execute(String.format(url, id));
    }
}
