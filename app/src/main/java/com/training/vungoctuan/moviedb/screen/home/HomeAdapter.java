package com.training.vungoctuan.moviedb.screen.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.screen.BaseRecyclerViewAdapter;
import com.training.vungoctuan.moviedb.data.model.Movie;
import com.training.vungoctuan.moviedb.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vungoctuan on 2/28/18.
 */
public class HomeAdapter extends BaseRecyclerViewAdapter<HomeAdapter.ItemViewHolder> {
    private List<Movie> mMovies = new ArrayList<>();

    HomeAdapter(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    void updateData(List<Movie> movies) {
        if (movies == null) return;
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageMovie;
        private TextView mTextName, mTextRate;

        ItemViewHolder(View view) {
            super(view);
            mImageMovie = view.findViewById(R.id.image_card_movie);
            mTextName = view.findViewById(R.id.text_card_name);
            mTextRate = view.findViewById(R.id.text_card_rate);
        }

        public void setData(Movie movie) {
            if (movie == null) return;
            mTextName.setText(movie.getTitle());
            mTextRate.setText(movie.getVoteAverage());
            Glide.with(itemView.getContext())
                .load(String.format(Constant.API_IMAGE_URL,movie.getPosterPath()))
                .into(mImageMovie);
        }
    }
}
