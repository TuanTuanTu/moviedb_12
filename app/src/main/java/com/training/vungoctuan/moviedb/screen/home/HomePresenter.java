package com.training.vungoctuan.moviedb.screen.home;

import com.training.vungoctuan.moviedb.data.model.Genre;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.repository.GenreRepository;
import com.training.vungoctuan.moviedb.data.repository.MovieRepository;
import com.training.vungoctuan.moviedb.data.source.GenreDataSource;
import com.training.vungoctuan.moviedb.data.source.MovieDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.GenreRemoteDataSource;
import com.training.vungoctuan.moviedb.util.Constant;

import java.util.List;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private MovieRepository mMovieRepository;
    private GenreRepository mGenreRepository;
    private int mPopularPage = 1;
    private int mNowPlayingPage = 1;
    private int mUpcomingPage = 1;
    private int mTopRatePage = 1;
    private boolean mIsPopularSuccess, mIsNowPlayingSuccess, mIsUpcomingSuccess,
        mIsTopRateSuccess, mIsGenresSuccess;

    HomePresenter(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
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
        mIsPopularSuccess = false;
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_POPULAR,
            Constant.ApiParameter.API_URL_LANGUAGE,
            mPopularPage,
            new MovieDataSource
                .LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mPopularPage++;
                    mIsPopularSuccess = true;
                    mView.onGetPopularMoviesSuccess(movies);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onGetPopularMoviesFailed();
                }
            });
    }

    @Override
    public void loadNowPlayingMovies() {
        mIsNowPlayingSuccess = false;
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_NOW_PLAYING,
            Constant.ApiParameter.API_URL_LANGUAGE,
            mNowPlayingPage,
            new MovieDataSource.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mNowPlayingPage++;
                    mIsNowPlayingSuccess = true;
                    mView.onGetNowPlayingMoviesSuccess(movies);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onGetNowPlayingMoviesFailed();
                }
            });
    }

    @Override
    public void loadUpcomingMovies() {
        mIsUpcomingSuccess = false;
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_UPCOMING,
            Constant.ApiParameter.API_URL_LANGUAGE,
            mUpcomingPage,
            new MovieDataSource.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mUpcomingPage++;
                    mIsUpcomingSuccess = true;
                    mView.onGetUpcomingMoviesSuccess(movies);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onGetUpcomingMoviesFailed();
                }
            });
    }

    @Override
    public void loadTopRateMovies() {
        mIsTopRateSuccess = false;
        mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_TOP_RATED,
            Constant.ApiParameter.API_URL_LANGUAGE,
            mTopRatePage,
            new MovieDataSource.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mTopRatePage++;
                    mIsTopRateSuccess = true;
                    mView.onGetTopRateMoviesSuccess(movies);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onGetTopRateMoviesFailed();
                }
            });
    }

    @Override
    public void loadGenresMovies() {
        mIsGenresSuccess = false;
        mGenreRepository.loadGenres(new GenreDataSource.LoadGenresCallback() {
            @Override
            public void onGenresLoaded(List<Genre> genres) {
                mIsGenresSuccess = true;
                mView.onGetGenresSuccess(genres);
            }

            @Override
            public void onDataNotAvailable() {
                mView.onGetGenresMoviesFailed();
            }
        });
    }

    //Checking if network available after reconnect, keep reload data
    @Override
    public void loadAfterNetworkChange() {
        if (!mIsPopularSuccess) {
            loadPopularMovies();
        }
        if (!mIsNowPlayingSuccess) {
            loadNowPlayingMovies();
        }
        if (!mIsTopRateSuccess) {
            loadTopRateMovies();
        }
        if (!mIsUpcomingSuccess) {
            loadUpcomingMovies();
        }
        if (!mIsGenresSuccess) {
            loadGenresMovies();
        }
    }
}
