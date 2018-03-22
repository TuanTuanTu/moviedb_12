package com.training.vungoctuan.moviedb.util;

import android.support.annotation.StringDef;

import com.training.vungoctuan.moviedb.BuildConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_GENRES;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIES_BY_GENRE;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIES_BY_PEOPLE;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIES_BY_PRODUCTION;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIES_WITH_CAST;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_CREDIT;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_DETAIL;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_GENRES;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_NOW_PLAYING;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_POPULAR;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_TOP_RATED;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_MOVIE_UPCOMING;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_SEARCH_MOVIES;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_SEARCH_QUERY;
import static com.training.vungoctuan.moviedb.util.Constant.ApiUrlDef.API_URL_TRAILER_MOVIE;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class Constant {
    static final int URL_REQUEST_TIMEOUT = 10000;
    static final int URL_CONNECT_TIMEOUT = 15000;
    public static final int SPLASH_TIMEOUT = 500;
    public static final int TEXT_OVERVIEW_MIN_LINES = 2;
    public static final int TEXT_OVERVIEW_MAX_LINES = 25;
    public static final String BUNDLE_MOVIE = "BUNDLE_MOVIE";
    public static final String BUNDLE_PEOPLE_ID = "BUNDLE_PEOPLE_ID";
    public static final String BUNDLE_PEOPLE_NAME = "BUNDLE_PEOPLE_NAME";
    public static final String BUNDLE_SEARCH_QUERY = "BUNDLE_SEARCH_QUERY";
    public static final String BUNDLE_TRAILER_KEY = "BUNDLE_TRAILER_KEY";
    public static final String BUNDLE_GENRE_ID = "BUNDLE_GENRE_ID";
    public static final String BUNDLE_GENRE_NAME = "BUNDLE_GENRE_NAME";
    private static final String API_KEY = "api_key=" + BuildConfig.API_KEY;
    public static final String API_KEY_2 = "?api_key=" + BuildConfig.API_KEY;
    public static final String API_KEY_YOUTUBE = BuildConfig.API_YOUTUBE_KEY;

    @StringDef({API_URL_MOVIE_POPULAR, API_URL_MOVIE_NOW_PLAYING,
        API_URL_MOVIE_UPCOMING, API_URL_MOVIE_TOP_RATED, API_URL_MOVIE_GENRES,
        API_URL_MOVIE_DETAIL, API_URL_MOVIE_CREDIT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ApiUrlDef {
        String API_URL_MOVIE_POPULAR = "movie/popular";
        String API_URL_MOVIE_NOW_PLAYING = "movie/now_playing";
        String API_URL_MOVIE_UPCOMING = "movie/upcoming";
        String API_URL_MOVIE_TOP_RATED = "movie/top_rated";
        String API_URL_MOVIE_GENRES = "genre/movie/list";
        String API_URL_MOVIE_DETAIL = "movie/";
        String API_URL_MOVIE_CREDIT = "movie/%s/credits?";
        String API_URL_MOVIES_BY_PEOPLE = "discover/movie?";
        String API_URL_MOVIES_BY_PRODUCTION = "company/%s/movies?";
        String API_URL_MOVIES_WITH_CAST = "&with_cast=%s";
        String API_URL_SEARCH_MOVIES = "search/movie?";
        String API_URL_SEARCH_QUERY = "&query=%s";
        String API_URL_TRAILER_MOVIE = "movie/%s/videos?";
        String API_URL_GENRES = "genre/movie/list?";
        String API_URL_MOVIES_BY_GENRE = "genre/%s/movies?";
    }

    public static class ApiRequestUrl {
        public static final String API_URL = "https://api.themoviedb.org/3/";
        public static final String API_IMAGE_URL = "https://image.tmdb.org/t/p/w500/%s";
        public static final String API_URL_REQUEST = API_URL + "%s?"
            + API_KEY + "&language=%s&page=%s";
        public static final String API_PRODUCTION_BY_ID_REQUEST = API_URL
            + API_URL_MOVIE_DETAIL + "%s?" + API_KEY;
        public static final String API_CREDIT_BY_MOVIE_REQUEST = API_URL
            + API_URL_MOVIE_CREDIT + API_KEY;
        static final String API_REQUEST_METHOD = "GET";
        public static final String API_MOVIES_BY_PEOPLE_REQUEST = API_URL
            + API_URL_MOVIES_BY_PEOPLE + API_KEY + API_URL_MOVIES_WITH_CAST;
        public static final String API_MOVIES_BY_PRODUCTION_REQUEST = API_URL
            + API_URL_MOVIES_BY_PRODUCTION + API_KEY;
        public static final String API_MOVIES_BY_SEARCH = API_URL
            + API_URL_SEARCH_MOVIES + API_KEY + API_URL_SEARCH_QUERY;
        public static final String API_TRAILER_BY_MOVIE_ID = API_URL
            + API_URL_TRAILER_MOVIE + API_KEY;
        public static final String API_GENRES_REQUEST = API_URL
            + API_URL_GENRES + API_KEY;
        public static final String API_MOVIES_BY_GENRE_REQUEST = API_URL
            + API_URL_MOVIES_BY_GENRE + API_KEY;
        public static final String API_MOVIES_CATEGORY_URL =
            "https://api.themoviedb.org/3/{movie_category}" +
                "?api_key=" + BuildConfig.API_KEY + "&language=en-US";
    }

    public static class ApiParameter {
        public static final String API_URL_LANGUAGE = "en-US";
        public static final String API_URL_MOVIE_CATEGORY = "movie_category";
        public static final String API_URL_PAGE = "page";
    }

    public static class ApiResultKey {
        public static final String API_KEY_RESULTS = "results";
        public static final String API_MOVIE_KEY_ID = "id";
        public static final String API_MOVIE_KEY_TITLE = "title";
        public static final String API_MOVIE_KEY_VOTE_AVERAGE = "vote_average";
        public static final String API_MOVIE_KEY_POSTER_PATH = "poster_path";
        public static final String API_MOVIE_KEY_BACKDROP_PATH = "backdrop_path";
        public static final String API_MOVIE_KEY_OVERVIEW = "overview";
        public static final String API_MOVIE_KEY_RELEASE_DATE = "release_date";
        public static final String API_KEY_PRODUCTION_RESULTS = "production_companies";
        public static final String API_PRODUCTION_KEY_ID = "id";
        public static final String API_PRODUCTION_KEY_NAME = "name";
        public static final String API_KEY_CREDIT_CAST = "cast";
        public static final String API_KEY_CREDIT_CREW = "crew";
        public static final String API_CREDIT_KEY_ID = "id";
        public static final String API_CREDIT_KEY_CAST_ID = "cast_id";
        public static final String API_CAST_KEY_CHARACTER = "character";
        public static final String API_CAST_KEY_NAME = "name";
        public static final String API_CAST_KEY_PROFILE_PATH = "profile_path";
        public static final String API_CREW_CREDIT_ID = "credit_id";
        public static final String API_CREW_DEPARTMENT = "department";
        public static final String API_CREW_NAME = "name";
        public static final String API_CREW_JOB = "job";
        public static final String API_CREW_KEY_PROFILE_PATH = "profile_path";
        public static final String API_TRAILER_ID = "id";
        public static final String API_TRAILER_KEY = "key";
        public static final String API_TRAILER_NAME = "name";
        public static final String API_KEY_GENRES = "genres";
        public static final String API_KEY_GENRES_ID = "id";
        public static final String API_KEY_GENRES_NAME = "name";
    }

    public static class MoviesDataBase {
        public static final String DATABASE_NAME = "moviesDatabase";
        public static final int DATABASE_VERSION = 1;
        //Table
        public static final String TABLE_FAVOURITE_MOVIES = "favouriteMovies";
        //Columns
        public static final String KEY_MOVIES_ID = "id";
        public static final String KEY_MOVIES_TITLE = "title";
        public static final String KEY_MOVIES_VOTE_AVERAGE = "voteAverage";
        public static final String KEY_MOVIES_POSTER_PATH = "posterPath";
        public static final String KEY_MOVIES_BACKDROP_PATH = "backdropPath";
        public static final String KEY_MOVIES_OVERVIEW = "overview";
        public static final String KEY_MOVIES_RELEASE_DATE = "releaseDate";
        //Query
        public static final String QUERY_CREATE_MOVIES = "CREATE TABLE " + TABLE_FAVOURITE_MOVIES +
            "(" +
            KEY_MOVIES_ID + " TEXT," +
            KEY_MOVIES_TITLE + " TEXT," +
            KEY_MOVIES_VOTE_AVERAGE + " TEXT," +
            KEY_MOVIES_POSTER_PATH + " TEXT," +
            KEY_MOVIES_BACKDROP_PATH + " TEXT," +
            KEY_MOVIES_OVERVIEW + " TEXT," +
            KEY_MOVIES_RELEASE_DATE + " TEXT" +
            ")";
        public static final String QUERY_DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";
        public static final String QUERY_SELECTION = " = ?";
    }
}
