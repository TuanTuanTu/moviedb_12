package com.training.vungoctuan.moviedb.screen.home;

import com.training.vungoctuan.moviedb.data.model.Genre;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.data.model.api.MovieResults;
import com.training.vungoctuan.moviedb.data.repository.GenreRepository;
import com.training.vungoctuan.moviedb.data.repository.MovieRepository;
import com.training.vungoctuan.moviedb.data.source.GenreDataSource;
import com.training.vungoctuan.moviedb.data.source.remote.GenreRemoteDataSource;
import com.training.vungoctuan.moviedb.util.Constant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

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

    private List<Movie> movies = new ArrayList<>();

    @Override
    public void loadPopularMovies() {
        mIsPopularSuccess = false;
        Observable<MovieResults> movieObservable = mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_POPULAR,
            mPopularPage
        );
        movieObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<MovieResults>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(MovieResults movieResults) {
                    mView.onGetPopularMoviesSuccess(movieResults.getMovies());
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    mView.onGetPopularMoviesFailed();
                }

                @Override
                public void onComplete() {
                    mPopularPage++;
                    mIsPopularSuccess = true;
                }
            });
    }

    @Override
    public void loadNowPlayingMovies() {
        mIsNowPlayingSuccess = false;
        Observable<MovieResults> movieObservable = mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_NOW_PLAYING,
            mNowPlayingPage
        );
        movieObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<MovieResults>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(MovieResults movieResults) {
                    mNowPlayingPage++;
                    mIsNowPlayingSuccess = true;
                    mView.onGetNowPlayingMoviesSuccess(movieResults.getMovies());
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    mView.onGetNowPlayingMoviesFailed();
                }

                @Override
                public void onComplete() {
                }
            });
    }

    @Override
    public void loadUpcomingMovies() {
        mIsUpcomingSuccess = false;
        Observable<MovieResults> movieObservable = mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_UPCOMING,
            mUpcomingPage
        );
        movieObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<MovieResults>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(MovieResults movieResults) {
                    mUpcomingPage++;
                    mIsUpcomingSuccess = true;
                    mView.onGetUpcomingMoviesSuccess(movieResults.getMovies());
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    mView.onGetUpcomingMoviesFailed();
                }

                @Override
                public void onComplete() {
                }
            });
    }

    @Override
    public void loadTopRateMovies() {
        mIsTopRateSuccess = false;
        Observable<MovieResults> movieObservable = mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_TOP_RATED,
            mTopRatePage
        );
        movieObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<MovieResults>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(MovieResults movieResults) {
                    mTopRatePage++;
                    mIsTopRateSuccess = true;
                    mView.onGetTopRateMoviesSuccess(movieResults.getMovies());
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    mView.onGetTopRateMoviesFailed();
                }

                @Override
                public void onComplete() {
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
    public boolean loadAfterNetworkChange() {
        boolean isLoad = false;
        if (!mIsPopularSuccess) {
            loadPopularMovies();
            isLoad = true;
        }
        if (!mIsNowPlayingSuccess) {
            loadNowPlayingMovies();
            isLoad = true;
        }
        if (!mIsTopRateSuccess) {
            loadTopRateMovies();
            isLoad = true;
        }
        if (!mIsUpcomingSuccess) {
            loadUpcomingMovies();
            isLoad = true;
        }
        if (!mIsGenresSuccess) {
            loadGenresMovies();
            isLoad = true;
        }
        return isLoad;
    }

    private void filterMovieByRate(){
        Observable<MovieResults> movieObservable = mMovieRepository.getMoviesByCategories(
            Constant.ApiUrlDef.API_URL_MOVIE_NOW_PLAYING,
            mNowPlayingPage
        );
        movieObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(new Function<MovieResults, ObservableSource<List<Movie>>>
                () {
                @Override
                public ObservableSource<List<Movie>> apply(MovieResults movieResults)
                    throws Exception {
                    return Observable.just(movieResults.getMovies());
                }
            })
            .flatMap(new Function<List<Movie>, ObservableSource<Movie>>() {
                @Override
                public ObservableSource<Movie> apply(List<Movie> movies) throws Exception {
                    return Observable.fromIterable(movies);
                }
            })
            .filter(new Predicate<Movie>() {
                @Override
                public boolean test(Movie movie) throws Exception {
                    return movie.getVoteAverage() > 7;
                }
            })
            .toList()
            .subscribe(new SingleObserver<List<Movie>>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onSuccess(List<Movie> movies) {
                    mPopularPage++;
                    mIsPopularSuccess = true;
                    mView.onGetPopularMoviesSuccess(movies);
                }

                @Override
                public void onError(Throwable e) {
                    mView.onGetPopularMoviesFailed();
                }
            });
    }
}
