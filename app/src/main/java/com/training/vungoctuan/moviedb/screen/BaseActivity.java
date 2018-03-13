package com.training.vungoctuan.moviedb.screen;

import android.support.v7.app.AppCompatActivity;

import com.training.vungoctuan.moviedb.data.repository.MovieRepository;
import com.training.vungoctuan.moviedb.data.source.local.MovieLocalDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.MovieRemoteDataSource;

/**
 * Created by vungoctuan on 2/28/18.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public MovieRepository getMovieRepository() {
        return MovieRepository.getInstance(
            MovieRemoteDataSource.getInstance(),
            MovieLocalDataSource.getInstance(this)
        );
    }
}
