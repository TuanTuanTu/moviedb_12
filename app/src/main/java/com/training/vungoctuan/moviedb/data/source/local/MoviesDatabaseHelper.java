package com.training.vungoctuan.moviedb.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.util.Constant.MoviesDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vungoctuan on 3/12/18.
 */
public class MoviesDatabaseHelper extends SQLiteOpenHelper {
    private static MoviesDatabaseHelper sInstance;

    public static MoviesDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MoviesDatabaseHelper(context);
        }
        return sInstance;
    }

    private MoviesDatabaseHelper(Context context) {
        super(context, MoviesDataBase.DATABASE_NAME, null, MoviesDataBase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MoviesDataBase.QUERY_CREATE_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL(
                MoviesDataBase.QUERY_DROP_TABLE_IF_EXISTS + MoviesDataBase.TABLE_FAVOURITE_MOVIES);
            onCreate(sqLiteDatabase);
        }
    }

    public List<Movie> getAllMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        String selectAllQuery =
            String.format(MoviesDataBase.QUERY_SELECT_ALL_FROM,
                MoviesDataBase.TABLE_FAVOURITE_MOVIES);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery, null);
        try {
            if (cursor.moveToNext()) {
                do {
                    Movie movie = new Movie();
                    movie.setId(cursor.getString(
                        cursor.getColumnIndex(MoviesDataBase.KEY_MOVIES_ID)));
                    movie.setTitle(cursor.getString(
                        cursor.getColumnIndex(MoviesDataBase.KEY_MOVIES_TITLE)));
                    movie.setVoteAverage(cursor.getString(
                        cursor.getColumnIndex(MoviesDataBase.KEY_MOVIES_VOTE_AVERAGE)));
                    movie.setPosterPath(cursor.getString(
                        cursor.getColumnIndex(MoviesDataBase.KEY_MOVIES_POSTER_PATH)));
                    movie.setBackdropPath(cursor.getString(
                        cursor.getColumnIndex(MoviesDataBase.KEY_MOVIES_BACKDROP_PATH)));
                    movie.setOverview(cursor.getString(
                        cursor.getColumnIndex(MoviesDataBase.KEY_MOVIES_OVERVIEW)));
                    movie.setReleaseDate(cursor.getString(
                        cursor.getColumnIndex(MoviesDataBase.KEY_MOVIES_RELEASE_DATE)));
                    movies.add(movie);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            db.close();
        }
        return movies;
    }

    public void addMovies(Movie movie) throws Exception {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(MoviesDataBase.KEY_MOVIES_ID, movie.getId());
            values.put(MoviesDataBase.KEY_MOVIES_TITLE, movie.getTitle());
            values.put(MoviesDataBase.KEY_MOVIES_VOTE_AVERAGE, movie.getVoteAverage());
            values.put(MoviesDataBase.KEY_MOVIES_POSTER_PATH, movie.getPosterPath());
            values.put(MoviesDataBase.KEY_MOVIES_BACKDROP_PATH, movie.getBackdropPath());
            values.put(MoviesDataBase.KEY_MOVIES_OVERVIEW, movie.getOverview());
            values.put(MoviesDataBase.KEY_MOVIES_RELEASE_DATE, movie.getReleaseDate());
            db.insertOrThrow(MoviesDataBase.TABLE_FAVOURITE_MOVIES, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public void deleteMovies(Movie movie) throws Exception {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            String selection = MoviesDataBase.KEY_MOVIES_ID + MoviesDataBase.QUERY_WHERE;
            String[] selectionArgs = {movie.getId()};
            db.delete(MoviesDataBase.TABLE_FAVOURITE_MOVIES, selection, selectionArgs);
        } finally {
            db.endTransaction();
            db.close();
        }
    }
}
