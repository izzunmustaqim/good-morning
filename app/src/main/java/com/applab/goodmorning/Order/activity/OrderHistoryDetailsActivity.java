package com.applab.goodmorning.Order.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applab.goodmorning.Order.adapter.OrderDetailsAdapter;
import com.applab.goodmorning.Order.model.Order;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.applab.goodmorning.Welcome.model.Products;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * -- =============================================
 * -- Author     : Muhammad Izzun Mustaqim Bin Ismahdi
 * -- Create date: 9/3/2016
 * -- Description: OrderHistoryDetailsActivity .java
 * -- =============================================
 * HISTORY OF UPDATE
 * <p/>
 * NO     DEVELOPER         DATETIME                      DESCRIPTION
 * *******************************************************************************
 * 1
 * 2
 */

public class OrderHistoryDetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ImageButton mImg;
    private OrderDetailsAdapter mAdapter;
    private Order mOrder;

    private TextView mTxtDate, mTxtOrderCode, mTxtStatus, mTxtSubTotal, mTxtShippingFee, mTxtDiscount, mTxtGST, mTxtTotal, mTxtPromotionalCode;
    private LinearLayout mLnPromotionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_details);

        mOrder = getIntent().getParcelableExtra("OrderDetails");

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_order_history));

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);

        mImg = (ImageButton) mToolbar.findViewById(R.id.img);
        mImg.setVisibility(View.GONE);

        setData();

        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new OrderDetailsAdapter(OrderHistoryDetailsActivity.this, mOrder);
        mLayoutManager = new LinearLayoutManager(OrderHistoryDetailsActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    public void setData() {
        mTxtDate = (TextView) findViewById(R.id.txtDate);
        mTxtOrderCode = (TextView) findViewById(R.id.txtOrderCode);
        mTxtStatus = (TextView) findViewById(R.id.txtStatus);
        mTxtSubTotal = (TextView) findViewById(R.id.txtSubTotal);
        mTxtShippingFee = (TextView) findViewById(R.id.txtShippingFee);
        mTxtDiscount = (TextView) findViewById(R.id.txtDiscount);
        mTxtGST = (TextView) findViewById(R.id.txtGST);
        mTxtTotal = (TextView) findViewById(R.id.txtTotal);
        mTxtPromotionalCode = (TextView) findViewById(R.id.txtPromotionalCode);
        mLnPromotionCode = (LinearLayout) findViewById(R.id.lnPromotionalCode);

        mTxtDate.setText(Utilities.setCalendarDate("yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", mOrder.getCreateDate()));
        mTxtOrderCode.setText(mOrder.getOrderId());
        mTxtStatus.setText(mOrder.getStatus());
        mTxtSubTotal.setText(mOrder.getCurrency() + String.valueOf(mOrder.getTotalPrice()));
        mTxtShippingFee.setText(mOrder.getCurrency() + String.valueOf(mOrder.getShippingPrice()));
        mTxtGST.setText(mOrder.getCurrency() + String.valueOf(mOrder.getGstPrice()));
        mTxtTotal.setText(String.valueOf(mOrder.getTotalPriceIncGst()));

        if (mOrder.getPromotionalCode() == null) {
            mLnPromotionCode.setVisibility(View.GONE);
            mTxtDiscount.setText(mOrder.getCurrency() + " 0");
        } else {
            mTxtPromotionalCode.setText(mOrder.getPromotionalCode());
            mTxtDiscount.setText(mOrder.getCurrency() + String.valueOf(mOrder.getPromotionalValue()));
        }
    }
}
