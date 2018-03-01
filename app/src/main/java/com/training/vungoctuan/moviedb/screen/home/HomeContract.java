package com.training.vungoctuan.moviedb.screen.home;

import com.training.vungoctuan.moviedb.screen.BasePresenter;
import com.training.vungoctuan.moviedb.screen.data.model.Movie;

import java.util.List;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class HomeContract {
    public interface View {
        void onGetMoviesSuccess(List<Movie> movies);
    }

    public interface Presenter extends BasePresenter<View> {
        void loadPopularMovies();
    }
}
