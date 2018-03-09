package com.training.vungoctuan.moviedb.screen.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.screen.BaseRecyclerViewAdapter;
import com.training.vungoctuan.moviedb.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vungoctuan on 3/1/18.
 */
public class MoviesAdapter extends BaseRecyclerViewAdapter<MoviesAdapter.ItemViewHolder> {
    private List<Movie> mMovies = new ArrayList<>();
    private LoadMoviesCallback mCallback;

    protected MoviesAdapter(@NonNull Context context,
                            LoadMoviesCallback callback) {
        super(context);
        mCallback = callback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_movie_horizontal,
            parent, false);
        return new ItemViewHolder(view, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(mMovies.get(position));
    }

    void updateData(List<Movie> movies) {
        if (movies == null) return;
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    void clearData() {
        if (mMovies == null) return;
        mMovies.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImagePoster;
        private TextView mTextTitle, mTextOverview, mTextRate;
        private Movie mMovie;

        ItemViewHolder(View view,
                       final LoadMoviesCallback callback) {
            super(view);
            mImagePoster = view.findViewById(R.id.image_card_movie);
            mTextTitle = view.findViewById(R.id.text_card_name);
            mTextOverview = view.findViewById(R.id.text_card_overview);
            mTextRate = view.findViewById(R.id.text_card_rate);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback == null) return;
                    callback.onItemClicked(mMovie);
                }
            });
        }

        void setData(Movie movie) {
            if (movie == null) return;
            mMovie = movie;
            ImageUtils.loadImageFromUrl(
                mImagePoster,
                movie.getPosterPath(),
                R.drawable.poster_3);
            mTextTitle.setText(movie.getTitle());
            mTextOverview.setText(movie.getOverview());
            mTextRate.setText(movie.getVoteAverage());
        }
    }

    interface LoadMoviesCallback {
        void onItemClicked(Movie movie);
    }
}

