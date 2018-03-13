package com.training.vungoctuan.moviedb.screen.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by vungoctuan on 3/12/18.
 */
public class MoviesByFavourite extends MoviesActivity {
    public static Intent getInstance(Context context) {
        return new Intent(context, MoviesByFavourite.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
