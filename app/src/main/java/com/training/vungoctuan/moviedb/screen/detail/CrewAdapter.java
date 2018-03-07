package com.training.vungoctuan.moviedb.screen.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.data.model.credit.Crew;
import com.training.vungoctuan.moviedb.screen.BaseRecyclerViewAdapter;
import com.training.vungoctuan.moviedb.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vungoctuan on 3/6/18.
 */
public class CrewAdapter extends BaseRecyclerViewAdapter<CrewAdapter.ItemViewHolder> {
    private List<Crew> mCrews = new ArrayList<>();

    protected CrewAdapter(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
            getContext()).inflate(R.layout.item_credit, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(mCrews.get(position));
    }

    @Override
    public int getItemCount() {
        return mCrews == null ? 0 : mCrews.size();
    }

    void updateData(List<Crew> crews) {
        if (crews == null) return;
        mCrews.clear();
        mCrews.addAll(crews);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextName, mTextRole;

        ItemViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.image_card_credit);
            mTextName = view.findViewById(R.id.text_card_credit_name);
            mTextRole = view.findViewById(R.id.text_card_credit_role);
        }

        public void setData(Crew crew) {
            if (crew == null) return;
            ImageUtils.loadImageFromUrl(
                mImageView,
                crew.getProfilePath(),
                R.drawable.ic_avatar_man);
            mTextName.setText(crew.getName());
            mTextRole.setText(crew.getDepartment());
        }
    }
}
