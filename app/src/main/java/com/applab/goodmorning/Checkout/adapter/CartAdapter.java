package com.applab.goodmorning.Checkout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Checkout.fragment.CartConfirmationFragment;
import com.applab.goodmorning.Checkout.holder.CartHolder;
import com.applab.goodmorning.Checkout.model.CartItem;
import com.applab.goodmorning.Checkout.webapi.HttpHelper;
import com.applab.goodmorning.Products.model.Product;
import com.applab.goodmorning.Products.model.ProductItems;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Welcome.model.Products;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 4/3/2016
 * -- Description: CartAdapter .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1    Edwin Cheong       16/3/2016       Fix Ui Problems & Adding Numbers Buttons
 * 2
 */
public class CartAdapter extends RecyclerView.Adapter<CartHolder> {
    private Context mContext;
    private CartItem mCartItem;
    private LayoutInflater mInflater;
    private String TAG;
    private View mOrderSummary;

    public CartAdapter(Context context, CartItem cartItem, String TAG, View orderSummary) {
        mContext = context;
        this.TAG = TAG;
        mCartItem = cartItem;
        mOrderSummary = orderSummary;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.custom_cart, parent, false);
        return new CartHolder(v);
    }

    @Override
    public void onBindViewHolder(final CartHolder holder, final int position) {
        Product product = mCartItem.getCarts().get(0).getProducts().get(position);
        product.setPosition(position);
        holder.getTxtProductName().setText(product.getProductTitle());
        holder.getTxtProductWeight().setText(product.getWeight());
        holder.getTxtProductPrice().setText(product.getPrice());
        holder.getTxtAmount().setText(String.valueOf(product.getQuantity()));
        holder.getBtnPlus().setTag(product);
        holder.getBtnMinus().setTag(product);
        setBtnPlusOnClick(holder.getBtnPlus(), holder.getTxtAmount());
        setBtnMinusOnClick(holder.getBtnMinus(), holder.getTxtAmount());

        Glide.with(mContext)
                .load(product.getCoverImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.empty_photo)
                .into(holder.getImgProducts());
    }

    private void setBtnPlusOnClick(final RelativeLayout btnPlus, final TextView txtAmount) {
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = (Product) btnPlus.getTag();
                int oldCount = product.getQuantity();
                int newCount = product.getQuantity() + 1;
                product.setQuantity(newCount);
                txtAmount.setText(String.valueOf(newCount));
                HttpHelper.putCart(mContext, TAG, product, txtAmount, oldCount, newCount);
            }
        });
    }

    private void setBtnMinusOnClick(final RelativeLayout btnPlus, final TextView txtAmount) {
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = (Product) btnPlus.getTag();
                int oldCount = product.getQuantity();
                int newCount = product.getQuantity() - 1;
                product.setQuantity(newCount);
                if (newCount == 0) {
                    HttpHelper.deleteCart(mContext, TAG, product.getProductId());
                    removeAt(product.getPosition());
                } else {
                    HttpHelper.putCart(mContext, TAG, product, txtAmount, oldCount, newCount);
                }
                txtAmount.setText(String.valueOf(newCount));

            }
        });
    }

    public void removeAt(int position) {
        mCartItem.getCarts().get(0).getProducts().remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mCartItem.getCarts().get(0).getProducts().size());
        if(mCartItem.getCarts().get(0).getProducts().size()==0){
            mOrderSummary.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mCartItem == null ? 0 : mCartItem.getCarts().get(0).getProducts().size();
    }

    public void swapProduct(CartItem cartItem) {
        mCartItem = cartItem;
        this.notifyDataSetChanged();
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }
}
