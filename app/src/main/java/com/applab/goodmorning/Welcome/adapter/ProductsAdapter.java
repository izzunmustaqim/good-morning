package com.applab.goodmorning.Welcome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.Checkout.webapi.HttpHelper;
import com.applab.goodmorning.Login.database.CRUDHelper;
import com.applab.goodmorning.Products.activity.ProductDetailsActivity;
import com.applab.goodmorning.Products.model.Product;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Welcome.activity.WelcomeActivity;
import com.applab.goodmorning.Welcome.holder.ProductsHolder;
import com.applab.goodmorning.Welcome.model.Products;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by user on 04-Mar-16.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsHolder> {
    private Context mContext;
    private ArrayList<Product> mProducts = new ArrayList<>();
    private LayoutInflater mInflater;
    private String TAG = WelcomeActivity.class.getSimpleName();

    public ProductsAdapter(Context context, ArrayList<Product> products) {
        mContext = context;
        mProducts = products;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ProductsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.custom_products_row, parent, false);
        return new ProductsHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductsHolder holder, final int position) {
        holder.getTxtProductName().setText(mProducts.get(position).getProductTitle());
        holder.getTxtProductWeight().setText(Html.fromHtml(mProducts.get(position).getProductDescription()));
        holder.getTxtProductPrice().setText(mProducts.get(position).getPrice());
        holder.getBtnReadMore().setTag(mProducts.get(position));
        holder.getBtnReadMore().setOnClickListener(readMoreOnClickListener);
        holder.getBtnAddToCart().setTag(mProducts.get(position));
        holder.getBtnAddToCart().setOnClickListener(addToCartOnClickListener);

        Glide.with(mContext)
                .load(mProducts.get(position).getProductImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.empty_photo)
                .into(holder.getImgProducts());
    }

    @Override
    public int getItemCount() {
        return mProducts == null ? 0 : mProducts.size();
    }

    private View.OnClickListener addToCartOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Product product = (Product) ((RelativeLayout) v.findViewById(R.id.btnAddToCart)).getTag();
            if (Utilities.isLogin(mContext)) {
                HttpHelper.postCart(mContext, TAG, product.getId(), true);
            }
        }
    };

    private View.OnClickListener readMoreOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Product product = (Product) ((RelativeLayout) v.findViewById(R.id.btnReadMore)).getTag();
            Intent intent = new Intent(mContext, ProductDetailsActivity.class);
            intent.putExtra("id",product.getId());
            mContext.startActivity(intent);
        }
    };

    public void swapProducts(ArrayList<Product> products) {
        this.mProducts = products;
        this.notifyDataSetChanged();
    }
}
