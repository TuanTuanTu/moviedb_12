package com.training.vungoctuan.moviedb.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.data.model.Genre;
import com.training.vungoctuan.moviedb.util.Constant;

/**
 * Created by vungoctuan on 3/10/18.
 */
public class MoviesByGenreActivity extends MoviesActivity {
    public static Intent getInstance(Context context, Genre genre) {
        Intent intent = new Intent(context, MoviesByGenreActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.BUNDLE_GENRE_ID, genre.getId());
        intent.putExtra(Constant.BUNDLE_GENRE_NAME, genre.getName());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.loadMovieFromApi(
            Constant.ApiRequestUrl.API_MOVIES_BY_GENRE_REQUEST,
            getIntent().getStringExtra(Constant.BUNDLE_GENRE_ID));
        setTitle(String.format(
            getString(R.string.title_results_genre),
            getIntent().getStringExtra(Constant.BUNDLE_GENRE_NAME)));
    }
}
