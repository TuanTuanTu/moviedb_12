package com.training.vungoctuan.moviedb.screen.detail;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.model.Production;
import com.training.vungoctuan.moviedb.data.model.Trailer;
import com.training.vungoctuan.moviedb.data.model.credit.Credit;
import com.training.vungoctuan.moviedb.screen.BasePresenter;

import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public interface DetailContract {
    interface View {
        void onLoadProductionSuccess(List<Production> productions);
        void onLoadCreditSuccess(Credit credit);
        void onLoadTrailerSuccess(List<Trailer> trailers);
        void onLoadProductionFailed();
        void onLoadCreditFailed();
        void onLoadTrailerFailed();
        void onAddFavouriteSuccess(Movie movie);
        void onAddFavouriteFailed();
        void onDeleteFavouriteSuccess(Movie movie);
        void onDeleteFavouriteFailed();
        void isFavouriteMovie(boolean isFavourite);
    }

    interface Presenter extends BasePresenter<View> {
        void loadProductionsByMovieId(String movieId);
        void loadCreditByMovieId(String movieId);
        void loadTrailerByMovieId(String movieId);
        void addMovieToFavourite(Movie movie);
        void deleteMovieFromFavourite(Movie movie);
        boolean checkMovieFavouriteExisting(String movieId);
    }
}
