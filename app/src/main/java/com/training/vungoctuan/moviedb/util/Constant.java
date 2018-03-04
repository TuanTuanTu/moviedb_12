package com.training.vungoctuan.moviedb.util;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class Constant {
    static final int URL_REQUEST_TIMEOUT = 10000;
    static final int URL_CONNECT_TIMEOUT = 15000;
    private static final String API_KEY = "api_key=";
    private static final String API_URL = "https://api.themoviedb.org/3/";
    public static final String API_IMAGE_URL = "https://image.tmdb.org/t/p/w500/%s";
    static final String API_KEY_RESULTS = "results";
    public static final String API_URL_REQUEST = API_URL + "%s?"
        + API_KEY + "&language=%s&page=%s";
    static final String API_REQUEST_METHOD = "GET";
    public static final String API_URL_MOVIE_POPULAR = "movie/popular";
    public static final String API_URL_MOVIE_NOW_PLAYING = "movie/now_playing";
    public static final String API_URL_MOVIE_UPCOMING = "movie/upcoming";
    public static final String API_URL_MOVIE_TOP_RATED = "movie/top_rated";
    public static final String API_URL_MOVIE_GENRES = "genre/movie/list";
    public static final String API_URL_LANGUAGE = "en-US";
    public static final String API_URL_FIRST_PAGE = "1";
    static final String API_MOVIE_KEY_TITLE = "title";
    static final String API_MOVIE_KEY_POSTER_PATH = "poster_path";
    static final String API_MOVIE_KEY_VOTE_AVERAGE = "vote_average";
}
