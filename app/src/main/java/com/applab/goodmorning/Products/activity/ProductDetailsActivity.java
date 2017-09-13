package com.applab.goodmorning.Products.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.Checkout.webapi.HttpHelper;
import com.applab.goodmorning.Image.activity.ImageSlidingActivty;
import com.applab.goodmorning.Login.database.CRUDHelper;
import com.applab.goodmorning.Products.adapter.ProductDetailsAdapter;
import com.applab.goodmorning.Products.model.Product;
import com.applab.goodmorning.Products.model.ProductItems;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.ItemClickSupport;
import com.applab.goodmorning.Utilities.Utilities;


import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTxtToolbarTitle;
    private View mSideMenu;
    private RelativeLayout mRl;
    private RelativeLayout mFadeProgressBarRL;
    private ProgressBar mProgressBar;
    private TextView mTxtProfileName;
    private LinearLayout mBtnSignUp;
    private LinearLayout mBtnAccount;
    private LinearLayout mBtnHistory;
    private LinearLayout mBtnLogOut;
    private LinearLayout mBtnRegister;

    private TextView mTxtProductName;
    private TextView mTxtProductWeight;
    private TextView mTxtProductDesc;
    private LinearLayout mBtnAddToCart;
    private TextView mTxtProductPrice;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerview;
    private ProductDetailsAdapter mAdapter;

    private String TAG = ProductDetailsActivity.class.getSimpleName();
    private Product mProduct;
    private int mId = 0;
    private View mActionShop;

    private ImageView mImgProduct;

    private RelativeLayout mBtnProductOverView, mBtnMainIngredients,
            mBtnRecommendedFor, mBtnBenefits, mBtnDirectionsForUse, mBtnCertificates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        mTxtToolbarTitle = (TextView) mToolbar.findViewById(R.id.txtTitle);
        mTxtToolbarTitle.setText(getString(R.string.title_activity_products));

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        mImgProduct = (ImageView) findViewById(R.id.imgProduct);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);

        mAdapter = new ProductDetailsAdapter(ProductDetailsActivity.this, mProduct, mImgProduct);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mSideMenu = (View) findViewById(R.id.sideMenu);
        mBtnSignUp = (LinearLayout) mSideMenu.findViewById(R.id.btnSignIn);
        mBtnAccount = (LinearLayout) mSideMenu.findViewById(R.id.btnAccount);
        mBtnHistory = (LinearLayout) mSideMenu.findViewById(R.id.btnHistory);
        mBtnLogOut = (LinearLayout) mSideMenu.findViewById(R.id.btnSignOut);
        mBtnRegister = (LinearLayout) mSideMenu.findViewById(R.id.btnRegister);
        mTxtProfileName = (TextView) mSideMenu.findViewById(R.id.txtProfileName);
        mRl = (RelativeLayout) findViewById(R.id.fadeRL);
        mFadeProgressBarRL = (RelativeLayout) findViewById(R.id.fadeProgressBarRL);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        Utilities.setFadeProgressBarVisibility(false, mRl, mFadeProgressBarRL);
        Utilities.setSideMenuOnClickListener(this, mTxtProfileName, mBtnSignUp, mBtnRegister, mBtnAccount, mBtnHistory, mBtnLogOut, mSideMenu, mRl);

        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(mToolbarOnClickListener);

        mTxtProductName = (TextView) findViewById(R.id.txtProductName);
        mTxtProductWeight = (TextView) findViewById(R.id.txtProductWeight);
        mTxtProductDesc = (TextView) findViewById(R.id.txtDesc);
        mTxtProductPrice = (TextView) findViewById(R.id.txtProductPrice);
        mImgProduct = (ImageView) findViewById(R.id.imgProducts);
        mBtnAddToCart = (LinearLayout) findViewById(R.id.btnAddToCart);

        mBtnProductOverView = (RelativeLayout) findViewById(R.id.btnProductOverview);
        mBtnMainIngredients = (RelativeLayout) findViewById(R.id.btnMainIngredients);
        mBtnRecommendedFor = (RelativeLayout) findViewById(R.id.btnRecommendFor);
        mBtnBenefits = (RelativeLayout) findViewById(R.id.btnBenefits);
        mBtnDirectionsForUse = (RelativeLayout) findViewById(R.id.btnDirectionsForUse);
        mBtnCertificates = (RelativeLayout) findViewById(R.id.btnCertificates);

        mBtnProductOverView.setOnClickListener(mBtnOverViewOnClickListener);
        mBtnMainIngredients.setOnClickListener(mBtnMainIngredientsOnClickListener);
        mBtnRecommendedFor.setOnClickListener(mBtnRecommendedForOnClickListener);
        mBtnBenefits.setOnClickListener(mBtnOnBenefitsClickListener);
        mBtnDirectionsForUse.setOnClickListener(mBtnOnDirectionClickListener);
        mBtnCertificates.setOnClickListener(mBtnOnCertificatesClickListener);
        mBtnAddToCart.setOnClickListener(mBtnAddCartOnClickListener);

        mId = getIntent().getIntExtra("id", 0);
        ItemClickSupport.addTo(mRecyclerview).setOnItemClickListener(mItemClickListener);
    }

    private ItemClickSupport.OnItemClickListener mItemClickListener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
            mAdapter.setPosition(position);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        Utilities.checkSideMenuOnPause(this, mSideMenu, mRl);
        LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDelay(this);
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(broadcastReceiver, iff);
        com.applab.goodmorning.Products.webapi.HttpHelper.getProductDetails(mId, ProductDetailsActivity.this, TAG);
    }

    private void setDelay(final Context context) {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Utilities.refreshActionShop(context, mActionShop);
            }
        };
        handler.postDelayed(r, 1000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_menu) {
            Utilities.setSideMenuVisible(true, mSideMenu, mRl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        mActionShop = (View) menu.findItem(R.id.action_shop).getActionView();
        Utilities.setAddCartOnClickListener(ProductDetailsActivity.this, mActionShop);
        return true;
    }

    private View.OnClickListener mToolbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                if (intent.getBooleanExtra("success", false)) {
                    Utilities.refreshActionShop(ProductDetailsActivity.this, mActionShop);
                    Utilities.setFadeProgressBarVisibility(false, mFadeProgressBarRL, mProgressBar);
                } else if (intent.getBooleanExtra("failed", false)) {
                    Utilities.setFadeProgressBarVisibility(false, mFadeProgressBarRL, mProgressBar);
                } else {
                    setData(intent);
                }
            }
        }
    };

    public void setData(Intent intent) {
        mProduct = intent.getParcelableExtra("Product");
        if (mProduct != null) {
            mTxtProductName.setText(mProduct.getProductTitle());
            mTxtProductWeight.setText(mProduct.getProductSubTitle());
            mTxtProductPrice.setText(mProduct.getPrice());
            mTxtProductDesc.setText(Html.fromHtml(mProduct.getProductDescription()));
            mAdapter.swapProduct(mProduct);
        }
    }

    private View.OnClickListener mBtnAddCartOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (CRUDHelper.getToken(ProductDetailsActivity.this) == null) {
                Utilities.showError(ProductDetailsActivity.this, "", getString(R.string.need_login));
            } else {
                Utilities.setFadeProgressBarVisibility(true, mRl, mFadeProgressBarRL);
                HttpHelper.postCart(ProductDetailsActivity.this, TAG, mId, false);
            }
        }
    };

    private View.OnClickListener mBtnOverViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProductDetailsActivity.this, ImageSlidingActivty.class);
            ArrayList<String> arr = new ArrayList<>();
            if (mProduct != null)
                if (mProduct.getProductExtras() != null)
                    if (mProduct.getProductExtras().getProductOverview() != null) {
                        arr.add(mProduct.getProductExtras().getProductOverview());
                        intent.putExtra("url", arr);
                        intent.putExtra("position", 0);
                        startActivity(intent);
                    }

        }
    };

    private View.OnClickListener mBtnMainIngredientsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProductDetailsActivity.this, ImageSlidingActivty.class);
            ArrayList<String> arr = new ArrayList<>();
            if (mProduct != null)
                if (mProduct.getProductExtras() != null)
                    if (mProduct.getProductExtras().getMainIngredients() != null) {
                        arr.add(mProduct.getProductExtras().getMainIngredients());
                        intent.putExtra("url", arr);
                        intent.putExtra("position", 0);
                        startActivity(intent);
                    }

        }
    };

    private View.OnClickListener mBtnRecommendedForOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProductDetailsActivity.this, ImageSlidingActivty.class);
            ArrayList<String> arr = new ArrayList<>();
            if (mProduct != null)
                if (mProduct.getProductExtras() != null)
                    if (mProduct.getProductExtras().getRecommendedFor() != null) {
                        arr.add(mProduct.getProductExtras().getRecommendedFor());
                        intent.putExtra("url", arr);
                        intent.putExtra("position", 0);
                        startActivity(intent);
                    }

        }
    };

    private View.OnClickListener mBtnOnBenefitsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProductDetailsActivity.this, ImageSlidingActivty.class);
            ArrayList<String> arr = new ArrayList<>();
            if (mProduct != null)
                if (mProduct.getProductExtras() != null)
                    if (mProduct.getProductExtras().getBenefits() != null) {
                        arr.add(mProduct.getProductExtras().getBenefits());
                        intent.putExtra("url", arr);
                        intent.putExtra("position", 0);
                        startActivity(intent);
                    }
        }
    };

    private View.OnClickListener mBtnOnDirectionClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProductDetailsActivity.this, ImageSlidingActivty.class);
            ArrayList<String> arr = new ArrayList<>();
            if (mProduct != null)
                if (mProduct.getProductExtras() != null)
                    if (mProduct.getProductExtras().getDirectionsForUse() != null) {
                        arr.add(mProduct.getProductExtras().getDirectionsForUse());
                        intent.putExtra("url", arr);
                        intent.putExtra("position", 0);
                        startActivity(intent);
                    }

        }
    };

    private View.OnClickListener mBtnOnCertificatesClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProductDetailsActivity.this, ImageSlidingActivty.class);
            ArrayList<String> arr = new ArrayList<>();
            if (mProduct != null)
                if (mProduct.getProductExtras() != null)
                    if (mProduct.getProductExtras().getCertificates() != null) {
                        arr.add(mProduct.getProductExtras().getCertificates());
                        intent.putExtra("url", arr);
                        intent.putExtra("position", 0);
                        startActivity(intent);
                    }
        }
    };
}
