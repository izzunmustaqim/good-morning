package com.applab.goodmorning.Promotions.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.Promotions.holder.PromotionHolder;
import com.applab.goodmorning.Promotions.model.Promotion;
import com.applab.goodmorning.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 11/4/2016
 * -- Description: PromotionAdapter .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class PromotionAdapter extends RecyclerView.Adapter<PromotionHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Promotion> mPromotion = new ArrayList<>();

    public PromotionAdapter(Context context, ArrayList<Promotion> promotion) {
        this.mContext = context;
        this.mPromotion = promotion;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public PromotionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_promotions, parent, false);
        return new PromotionHolder(view);
    }

    @Override
    public void onBindViewHolder(PromotionHolder holder, int position) {
        holder.getTvPromotion().setText(mPromotion.get(position).getTitle());
        holder.getTvPromotionDate().setText(mPromotion.get(position).getFromDate());
        holder.getTvPromotionDetails().setText(mPromotion.get(position).getDescription());

        Glide.with(mContext)
                .load(mPromotion.get(position).getImage())
                .placeholder(R.drawable.empty_photo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getIvProduct());
    }

    @Override
    public int getItemCount() {
        return mPromotion == null ? 0 : mPromotion.size();
    }

    public void swapPromotion(ArrayList<Promotion> promotions){
        this.mPromotion = promotions;
        this.notifyDataSetChanged();
    }
}

