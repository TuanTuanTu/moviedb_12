package com.training.vungoctuan.moviedb.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.model.Production;
import com.training.vungoctuan.moviedb.data.model.credit.Cast;
import com.training.vungoctuan.moviedb.data.model.credit.Credit;
import com.training.vungoctuan.moviedb.data.model.credit.Crew;
import com.training.vungoctuan.moviedb.screen.BaseActivity;
import com.training.vungoctuan.moviedb.screen.movies.MoviesByCastActivity;
import com.training.vungoctuan.moviedb.screen.movies.MoviesByCrewActivity;
import com.training.vungoctuan.moviedb.screen.movies.MoviesByProductionActivity;
import com.training.vungoctuan.moviedb.util.Constant;
import com.training.vungoctuan.moviedb.util.ImageUtils;

import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class DetailActivity extends BaseActivity implements DetailContract.View,
    View.OnClickListener {
    private DetailContract.Presenter mPresenter;
    private Button mButtonExpand;
    private TextView mTextDetailOverview;
    private ProgressBar mProgressBarProduction, mProgressBarCast, mProgressBarCrew;
    private boolean mExpanded;
    private ProductionAdapter mProductionAdapter;
    private CastAdapter mCastAdapter;
    private CrewAdapter mCrewAdapter;
    private Movie mMovie;
    private static Intent mIntent;

    public static Intent getInstance(Context context, Movie movie) {
        if (mIntent == null) {
            mIntent = new Intent(context, DetailActivity.class);
        }
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra(Constant.BUNDLE_MOVIE, movie);
        return mIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mMovie = getIntent().getParcelableExtra(Constant.BUNDLE_MOVIE);
        mPresenter = new DetailPresenter();
        mPresenter.setView(this);
        initToolbar();
        initButtonComponents();
        initImageViewComponents();
        initTextViewComponents();
        initDetailAdapters();
        initLayoutProductions();
        initLayoutCasts();
        initLayoutCrews();
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        mProgressBarCast.setVisibility(View.VISIBLE);
        mProgressBarCrew.setVisibility(View.VISIBLE);
        mProgressBarProduction.setVisibility(View.VISIBLE);
        mPresenter.loadProductionsByMovieId(mMovie.getId());
        mPresenter.loadCreditByMovieId(mMovie.getId());
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initDetailAdapters() {
        mProductionAdapter = new ProductionAdapter(this,
            new ProductionAdapter.LoadProductionDataCallback() {
                @Override
                public void onItemProductionClicked(Production production) {
                    startActivity(MoviesByProductionActivity.getInstance(
                        getApplicationContext(),
                        production));
                }
            });
        mCastAdapter = new CastAdapter(this,
            new CastAdapter.LoadCastDataCallback() {
                @Override
                public void onItemCastClicked(Cast cast) {
                    startActivity(MoviesByCastActivity.getInstance(
                        getApplicationContext(),
                        cast));
                }
            });
        mCrewAdapter = new CrewAdapter(this,
            new CrewAdapter.LoadCrewDataCallback() {
                @Override
                public void onItemCrewClicked(Crew crew) {
                    startActivity(MoviesByCrewActivity.getInstance(
                        getApplicationContext(),
                        crew));
                }
            });
    }

    private void initLayoutProductions() {
        View include = findViewById(R.id.include_production);
        mProgressBarProduction = include.findViewById(R.id.progressbar_recycler);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_production);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mProductionAdapter);
    }

    private void initLayoutCasts() {
        View include = findViewById(R.id.include_cast);
        mProgressBarCast = include.findViewById(R.id.progressbar_recycler);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_cast);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mCastAdapter);
    }

    private void initLayoutCrews() {
        View include = findViewById(R.id.include_crew);
        mProgressBarCrew = include.findViewById(R.id.progressbar_recycler);
        TextView textView = include.findViewById(R.id.text_recycler_title);
        textView.setText(R.string.title_crew);
        RecyclerView recyclerView = include.findViewById(R.id.recycler_movies);
        recyclerView.setAdapter(mCrewAdapter);
    }

    private void initButtonComponents() {
        mButtonExpand = findViewById(R.id.button_expand_overview);
        mButtonExpand.setOnClickListener(this);
    }

    private void initImageViewComponents() {
        ImageView imageDetailTrailer = findViewById(R.id.image_detail_trailer);
        ImageUtils.loadImageFromUrl(
            imageDetailTrailer,
            mMovie.getBackdropPath(),
            R.drawable.image_trailer);
        ImageView imageDetailPoster = findViewById(R.id.image_detail_movie);
        ImageUtils.loadImageFromUrl(
            imageDetailPoster,
            mMovie.getPosterPath(),
            R.drawable.image_trailer);
    }

    private void initTextViewComponents() {
        TextView textDetailName = findViewById(R.id.text_detail_name);
        textDetailName.setText(
            mMovie.getTitle()
        );
        TextView textDetailInformation = findViewById(R.id.text_detail_information);
        textDetailInformation.setText(
            String.format(
                getString(R.string.detail_activity_release),
                mMovie.getReleaseDate()
            ));
        TextView textDetailRate = findViewById(R.id.text_detail_rate);
        textDetailRate.setText(
            mMovie.getVoteAverage()
        );
        mTextDetailOverview = findViewById(R.id.text_detail_overview);
        mTextDetailOverview.setText(
            mMovie.getOverview()
        );
        mTextDetailOverview.setMaxLines(Constant.TEXT_OVERVIEW_MIN_LINES);
    }

    private void changeExpandOverview() {
        mExpanded = !mExpanded;
        String title;
        if (mExpanded) {
            mTextDetailOverview.setMaxLines(Constant.TEXT_OVERVIEW_MAX_LINES);
            title = getString(R.string.title_less);
        } else {
            mTextDetailOverview.setMaxLines(Constant.TEXT_OVERVIEW_MIN_LINES);
            title = getString(R.string.title_more);
        }
        mButtonExpand.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_expand_overview:
                changeExpandOverview();
                break;
        }
    }

    @Override
    public void onLoadProductionSuccess(List<Production> productions) {
        mProgressBarProduction.setVisibility(View.GONE);
        mProductionAdapter.updateData(productions);
    }

    @Override
    public void onLoadCreditSuccess(Credit credit) {
        mProgressBarCrew.setVisibility(View.GONE);
        mProgressBarCast.setVisibility(View.GONE);
        mCastAdapter.updateData(credit.getCasts());
        mCrewAdapter.updateData(credit.getCrews());
    }
}
