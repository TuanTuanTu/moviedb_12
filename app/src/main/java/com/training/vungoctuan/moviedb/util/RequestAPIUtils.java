package com.training.vungoctuan.moviedb.util;

import com.training.vungoctuan.moviedb.data.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by vungoctuan on 3/1/18.
 */
class RequestAPIUtils {
    static String getJsonStringFromUrl(String urlString) throws IOException {
        HttpsURLConnection urlConnection;
        URL url = new URL(urlString);
        urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod(Constant.API_REQUEST_METHOD);
        urlConnection.setReadTimeout(Constant.URL_REQUEST_TIMEOUT);
        urlConnection.setConnectTimeout(Constant.URL_CONNECT_TIMEOUT);
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        String jsonString = sb.toString();
        urlConnection.disconnect();
        return jsonString;
    }

    static List<Movie> parseJsonToMovies(String json) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray movieJsonArray = jsonObject.getJSONArray(Constant.API_KEY_RESULTS);
        for (int i = 0; i < movieJsonArray.length(); i++) {
            Movie movie = new Movie();
            movie.setTitle(movieJsonArray.getJSONObject(i)
                .getString(Constant.API_MOVIE_KEY_TITLE));
            movie.setPosterPath(movieJsonArray.getJSONObject(i)
                .getString(Constant.API_MOVIE_KEY_POSTER_PATH));
            movie.setVoteAverage(movieJsonArray.getJSONObject(i)
                .getString(Constant.API_MOVIE_KEY_VOTE_AVERAGE));
            movies.add(movie);
        }
        return movies;
    }
}
