package com.training.vungoctuan.moviedb.screen.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.training.vungoctuan.moviedb.R;
import com.training.vungoctuan.moviedb.data.model.Production;
import com.training.vungoctuan.moviedb.screen.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vungoctuan on 3/5/18.
 */
public class ProductionAdapter extends BaseRecyclerViewAdapter<ProductionAdapter.ItemViewHolder> {
    private List<Production> mProductions = new ArrayList<>();
    private DetailContract.LoadProductionDataCallback mCallback;

    ProductionAdapter(@NonNull Context context,
                      DetailContract.LoadProductionDataCallback callback) {
        super(context);
        mCallback = callback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        View view = LayoutInflater.from(
            getContext()).inflate(R.layout.item_production, parent, false);
        return new ItemViewHolder(view, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(mProductions.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductions == null ? 0 : mProductions.size();
    }

    void updateData(List<Production> productions) {
        if (productions == null) return;
        mProductions.clear();
        mProductions.addAll(productions);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private Button mButtonItemProduction;
        private Production mProduction;

        ItemViewHolder(View view,
                       final DetailContract.LoadProductionDataCallback callback) {
            super(view);
            mButtonItemProduction = view.findViewById(R.id.button_item_production);
            mButtonItemProduction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemProductionClicked(mProduction);
                }
            });
        }

        private void setData(Production production) {
            if (production == null) return;
            mProduction = production;
            mButtonItemProduction.setText(production.getName());
        }
    }
}
