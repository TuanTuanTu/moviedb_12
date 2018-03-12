package com.training.vungoctuan.moviedb.screen.detail;

import com.training.vungoctuan.moviedb.data.model.Production;
import com.training.vungoctuan.moviedb.data.model.Trailer;
import com.training.vungoctuan.moviedb.data.model.credit.Credit;
import com.training.vungoctuan.moviedb.data.repository.CreditRepository;
import com.training.vungoctuan.moviedb.data.repository.ProductionRepository;
import com.training.vungoctuan.moviedb.data.repository.TrailerRepository;
import com.training.vungoctuan.moviedb.data.source.CreditDataSource;
import com.training.vungoctuan.moviedb.data.source.ProductionDataSource;
import com.training.vungoctuan.moviedb.data.source.TrailerDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.CreditRemoteDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.ProductionRemoteDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.TrailerRemoteDataSource;

import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View mView;
    private ProductionRepository mProductionRepository;
    private CreditRepository mCreditRepository;
    private TrailerRepository mTrailerRepository;

    DetailPresenter() {
        mProductionRepository = ProductionRepository
            .getInstance(ProductionRemoteDataSource.getInstance());
        mCreditRepository = CreditRepository
            .getInstance(CreditRemoteDataSource.getInstance());
        mTrailerRepository = TrailerRepository
            .getInstance(TrailerRemoteDataSource.getInstance());
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
        mProductionRepository.getProductionByMovieId(movieId,
            new ProductionDataSource.LoadProductionsCallback() {
                @Override
                public void onProductionsLoaded(List<Production> productions) {
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
        mCreditRepository.getCreditByMovieId(movieId,
            new CreditDataSource.LoadProductionsCallback() {
                @Override
                public void onCreditLoaded(Credit credits) {
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
        mTrailerRepository.getTrailerByMovieId(movieId,
            new TrailerDataSource.LoadTrailersCallback() {
                @Override
                public void onTrailersLoaded(List<Trailer> trailers) {
                    mView.onLoadTrailerSuccess(trailers);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onLoadTrailerFailed();
                }
            });
    }
}
