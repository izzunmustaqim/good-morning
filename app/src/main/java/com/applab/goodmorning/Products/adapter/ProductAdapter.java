package com.applab.goodmorning.Products.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.applab.goodmorning.Checkout.webapi.HttpHelper;
import com.applab.goodmorning.Login.database.CRUDHelper;
import com.applab.goodmorning.Products.activity.ProductDetailsActivity;
import com.applab.goodmorning.Products.holder.ProductHolder;
import com.applab.goodmorning.Products.model.Product;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by user on 07-Mar-16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private Cursor mCursor;
    private RelativeLayout mRl;
    private ProgressBar mProgressBar;
    private String TAG;

    public ProductAdapter(Context context, Cursor cursor, RelativeLayout rl, ProgressBar progressBar, String TAG) {
        mContext = context;
        mCursor = cursor;
        mInflater = LayoutInflater.from(mContext);
        mRl = rl;
        mProgressBar = progressBar;
        this.TAG = TAG;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.custom_product_list, parent, false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        if (mCursor != null) {
            Product product = Product.getProduct(mCursor, position);
            holder.getTxtProductName().setText(product.getProductTitle());
            holder.getTxtProductPrice().setText(product.getPromotionPrice());
            holder.getTxtProductWeight().setText(product.getProductSubTitle());

            holder.getBtnAddToCart().setTag(product.getId());
            holder.getBtnAddToCart().setOnClickListener(addToCartOnClickListener);
            holder.getBtnReadMore().setTag(product.getId());
            holder.getBtnReadMore().setOnClickListener(readMoreOnClickListener);

            Glide.with(mContext)
                    .load(product.getProductImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.empty_photo)
                    .into(holder.getImgProduct());
        }
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    private View.OnClickListener addToCartOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Utilities.isLogin(mContext)) {
                RelativeLayout cart = (RelativeLayout) v.findViewById(R.id.btnAddToCart);
                int id = (int) cart.getTag();
                HttpHelper.postCart(mContext, TAG, id,false);
                Utilities.setFadeProgressBarVisibility(true, mRl, mProgressBar);
            }
        }
    };

    private View.OnClickListener readMoreOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RelativeLayout btnReadMore = (RelativeLayout) v.findViewById(R.id.btnReadMore);
            Intent intent = new Intent(mContext, ProductDetailsActivity.class);
            intent.putExtra("id", (int) btnReadMore.getTag());
            mContext.startActivity(intent);
        }
    };

    public Cursor swapCursor(Cursor cursor) {
        if (this.mCursor == cursor) {
            return null;
        }
        Cursor oldCursor = this.mCursor;
        this.mCursor = cursor;
        if (cursor != null) {
            notifyDataSetChanged();
        }
        return oldCursor;
    }
}
