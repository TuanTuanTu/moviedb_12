package com.training.vungoctuan.moviedb.screen.detail;

import com.training.vungoctuan.moviedb.data.model.Production;
import com.training.vungoctuan.moviedb.data.model.credit.Credit;
import com.training.vungoctuan.moviedb.data.repository.CreditRepository;
import com.training.vungoctuan.moviedb.data.repository.ProductionRepository;
import com.training.vungoctuan.moviedb.data.source.CreditDataSource;
import com.training.vungoctuan.moviedb.data.source.ProductionDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.CreditRemoteDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.ProductionRemoteDataSource;

import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View mView;
    private ProductionRepository mProductionRepository;
    private CreditRepository mCreditRepository;

    public DetailPresenter() {
        mProductionRepository = ProductionRepository
            .getInstance(ProductionRemoteDataSource.getInstance());
        mCreditRepository = CreditRepository
            .getInstance(CreditRemoteDataSource.getInstance());
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

                // TODO: 3/6/18 Show TextView to UI as "data not availble"
                @Override
                public void onDataNotAvailable() {
                }
            });
    }
}
