package com.training.vungoctuan.moviedb.screen.home;

import com.training.vungoctuan.moviedb.data.model.Genre;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.repository.GenreRepository;
import com.training.vungoctuan.moviedb.data.repository.MovieRepository;
import com.training.vungoctuan.moviedb.data.source.GenreDataSource;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.GenreRemoteDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.MovieRemoteDataSource;
import com.training.vungoctuan.moviedb.util.Constant;

import java.util.List;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private MovieRepository mMovieRepository;
    private GenreRepository mGenreRepository;

    HomePresenter() {
        mMovieRepository =
            MovieRepository.getInstance(MovieRemoteDataSource.getInstance());
        mGenreRepository =
            GenreRepository.getInstance(GenreRemoteDataSource.getInstance());
    }

    @Override
    public void setView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void loadPopularMovies() {
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_POPULAR,
            Constant.ApiParameter.API_URL_LANGUAGE,
            Constant.ApiParameter.API_URL_FIRST_PAGE,
            new MovieDataSource
                .LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mView.onGetPopularMoviesSuccess(movies);
                }

                // TODO: 3/10/18 callback
                @Override
                public void onDataNotAvailable() {
                }
            });
    }

    @Override
    public void loadNowPlayingMovies() {
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_NOW_PLAYING,
            Constant.ApiParameter.API_URL_LANGUAGE,
            Constant.ApiParameter.API_URL_FIRST_PAGE,
            new MovieDataSource.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mView.onGetNowPlayingMoviesSuccess(movies);
                }

                // TODO: 3/10/18 callback
                @Override
                public void onDataNotAvailable() {
                }
            });
    }

    @Override
    public void loadUpcomingMovies() {
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_UPCOMING,
            Constant.ApiParameter.API_URL_LANGUAGE,
            Constant.ApiParameter.API_URL_FIRST_PAGE,
            new MovieDataSource.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mView.onGetUpcomingMoviesSuccess(movies);
                }

                // TODO: 3/10/18 callback
                @Override
                public void onDataNotAvailable() {
                }
            });
    }

    @Override
    public void loadTopRateMovies() {
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_TOP_RATED,
            Constant.ApiParameter.API_URL_LANGUAGE,
            Constant.ApiParameter.API_URL_FIRST_PAGE,
            new MovieDataSource.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mView.onGetTopRateMoviesSuccess(movies);
                }

                // TODO: 3/10/18 callback
                @Override
                public void onDataNotAvailable() {
                }
            });
    }

    @Override
    public void loadGenresMovies() {
        mGenreRepository.loadGenres(new GenreDataSource.LoadGenresCallback() {
            @Override
            public void onGenresLoaded(List<Genre> genres) {
                mView.onGetGenresSuccess(genres);
            }

            // TODO: 3/10/18 callback
            @Override
            public void onDataNotAvailable() {
            }
        });
    }
}
