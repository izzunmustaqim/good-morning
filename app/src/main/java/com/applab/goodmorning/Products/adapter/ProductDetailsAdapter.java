package com.applab.goodmorning.Products.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.applab.goodmorning.Products.holder.ProductDetailsHolder;
import com.applab.goodmorning.Products.model.Product;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Welcome.model.Products;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by user on 29-Mar-16.
 */
public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private Product mProduct;
    private ImageView mImgProduct;
    private int mPosition = 0;

    public ProductDetailsAdapter(Context context, Product product, ImageView imgProduct) {
        mContext = context;
        mProduct = product;
        mImgProduct = imgProduct;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ProductDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.custom_products_details, parent, false);
        return new ProductDetailsHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductDetailsHolder holder, int position) {
        if (mPosition == position) {
            Glide.with(mContext)
                    .load(mProduct.getProductImageList().get(position))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.photo)
                    .into(mImgProduct);
            holder.getmView().setVisibility(View.VISIBLE);
        } else {
            holder.getmView().setVisibility(View.GONE);
        }
        Glide.with(mContext)
                .load(mProduct.getProductImageList().get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.empty_photo)
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return mProduct == null ? 0 : mProduct.getProductImageList().size();
    }

    public void setPosition(int position) {
        this.mPosition = position;
        this.notifyDataSetChanged();
    }

    public void swapProduct(Product product) {
        this.mProduct = product;
        this.notifyDataSetChanged();
    }
}