package com.training.vungoctuan.moviedb.screen.detail;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.model.Production;
import com.training.vungoctuan.moviedb.data.model.Trailer;
import com.training.vungoctuan.moviedb.data.model.credit.Credit;
import com.training.vungoctuan.moviedb.data.repository.CreditRepository;
import com.training.vungoctuan.moviedb.data.repository.MovieRepository;
import com.training.vungoctuan.moviedb.data.repository.ProductionRepository;
import com.training.vungoctuan.moviedb.data.repository.TrailerRepository;
import com.training.vungoctuan.moviedb.data.source.CreditDataSource;
import com.training.vungoctuan.moviedb.data.source.ProductionDataSource;
import com.training.vungoctuan.moviedb.data.source.TrailerDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.CreditRemoteDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.ProductionRemoteDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.TrailerRemoteDataSource;
import com.training.vungoctuan.moviedb.util.localtask.TaskAddFavourite;
import com.training.vungoctuan.moviedb.util.localtask.TaskCheckFavourite;
import com.training.vungoctuan.moviedb.util.localtask.TaskDeleteFavourite;

import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View mView;
    private ProductionRepository mProductionRepository;
    private CreditRepository mCreditRepository;
    private TrailerRepository mTrailerRepository;
    private MovieRepository mMovieRepository;
    private boolean mIsProductionSuccess, mIsCreditSuccess, mIsTrailerSuccess;

    DetailPresenter(MovieRepository movieRepository) {
        mProductionRepository = ProductionRepository
            .getInstance(ProductionRemoteDataSource.getInstance());
        mCreditRepository = CreditRepository
            .getInstance(CreditRemoteDataSource.getInstance());
        mTrailerRepository = TrailerRepository
            .getInstance(TrailerRemoteDataSource.getInstance());
        mMovieRepository = movieRepository;
    }

    @Override
    public void setView(DetailContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadProductionsByMovieId(String movieId) {
        mIsProductionSuccess = false;
        mProductionRepository.getProductionByMovieId(movieId,
            new ProductionDataSource.LoadProductionsCallback() {
                @Override
                public void onProductionsLoaded(List<Production> productions) {
                    mIsProductionSuccess = true;
                    mView.onLoadProductionSuccess(productions);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onLoadProductionFailed();
                }
            });
    }

    @Override
    public void loadCreditByMovieId(String movieId) {
        mIsCreditSuccess = false;
        mCreditRepository.getCreditByMovieId(movieId,
            new CreditDataSource.LoadProductionsCallback() {
                @Override
                public void onCreditLoaded(Credit credits) {
                    mIsCreditSuccess = true;
                    mView.onLoadCreditSuccess(credits);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onLoadCreditFailed();
                }
            });
    }

    @Override
    public void loadTrailerByMovieId(String movieId) {
        mIsTrailerSuccess = false;
        mTrailerRepository.getTrailerByMovieId(movieId,
            new TrailerDataSource.LoadTrailersCallback() {
                @Override
                public void onTrailersLoaded(List<Trailer> trailers) {
                    mIsTrailerSuccess = true;
                    mView.onLoadTrailerSuccess(trailers);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onLoadTrailerFailed();
                }
            });
    }

    @Override
    public void addMovieToFavourite(final Movie movie) {
        mMovieRepository.addMovieToLocal(movie, new TaskAddFavourite.AddFavouriteCallback() {
            @Override
            public void onAddSuccess() {
                mView.onAddFavouriteSuccess(movie);
            }

            @Override
            public void onAddFailed() {
                mView.onAddFavouriteFailed();
            }
        });
    }

    @Override
    public void deleteMovieFromFavourite(final Movie movie) {
        mMovieRepository.deleteMovieFromLocal(movie,
            new TaskDeleteFavourite.DeleteFavouriteCallback() {
                @Override
                public void onDeleteSuccess() {
                    mView.onDeleteFavouriteSuccess(movie);
                }

                @Override
                public void onDeleteFailed() {
                    mView.onDeleteFavouriteFailed();
                }
            });
    }

    @Override
    public void loadAfterNetworkChange(String movieId) {
        if (!mIsTrailerSuccess) {
            loadTrailerByMovieId(movieId);
        }
        if (!mIsCreditSuccess) {
            loadCreditByMovieId(movieId);
        }
        if (!mIsProductionSuccess) {
            loadProductionsByMovieId(movieId);
        }
    }

    @Override
    public void checkFavouriteMovie(Movie movie) {
        mMovieRepository.checkFavouriteMovie(movie, new TaskCheckFavourite.Callback() {
            @Override
            public void moviesExisting(Movie movie) {
                mView.onCheckFavouriteSuccess(movie);
            }

            @Override
            public void moviesNotExisting() {
                mView.onCheckFavouriteFailed();
            }
        });
    }
}
