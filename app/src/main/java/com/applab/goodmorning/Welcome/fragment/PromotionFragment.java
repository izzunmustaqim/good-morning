package com.applab.goodmorning.Welcome.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applab.goodmorning.Products.model.Product;
import com.applab.goodmorning.Products.model.ProductItems;
import com.applab.goodmorning.Products.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Welcome.adapter.ProductsAdapter;

import java.util.ArrayList;

/**
 * Created by user on 04-Mar-16.
 */
public class PromotionFragment extends Fragment {
    private ProductsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerview;
    ArrayList<Product> mProducts = new ArrayList<>();
    private static final String TAG = PromotionFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.myRecyclerView);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ProductsAdapter(getActivity(), mProducts);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mLayoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //HttpHelper.getProductPromo(getActivity(), TAG);
        HttpHelper.getProductPromo(getActivity(), TAG);
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getActivity().getBaseContext()).registerReceiver(broadcastReceiver, iff);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity().getBaseContext()).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                ProductItems product = intent.getParcelableExtra("ProductPromo");
                if(product!=null){
                    if (product.getItems() != null) {
                        mProducts = new ArrayList<>(product.getItems());
                        mAdapter.swapProducts(mProducts);
                    }
                }
            }
        }
    };
}
