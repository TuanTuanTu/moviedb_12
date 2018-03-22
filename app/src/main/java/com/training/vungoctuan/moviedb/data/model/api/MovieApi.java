package com.training.vungoctuan.moviedb.data.model.api;

import com.training.vungoctuan.moviedb.util.Constant;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vungoctuan on 3/19/18.
 */
public interface MovieApi {
    @GET(Constant.ApiRequestUrl.API_MOVIES_CATEGORY_URL)
    Observable<MovieResults> getPopularMovies(
        @Path(value = Constant.ApiParameter.API_URL_MOVIE_CATEGORY, encoded = true) String category,
        @Query(Constant.ApiParameter.API_URL_PAGE) int page);
}
