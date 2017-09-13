package com.applab.goodmorning.Checkout.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applab.goodmorning.Account.model.Account;
import com.applab.goodmorning.Checkout.activity.CartActivity;
import com.applab.goodmorning.Checkout.adapter.CartAdapter;
import com.applab.goodmorning.Checkout.adapter.ConfirmationAdapter;
import com.applab.goodmorning.Checkout.model.CartItem;
import com.applab.goodmorning.Checkout.model.CheckOut;
import com.applab.goodmorning.Checkout.webapi.HttpHelper;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Register.database.CRUDHelper;
import com.applab.goodmorning.Utilities.Utilities;
import com.google.gson.Gson;

public class CartConfirmationFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewOrderSummary;
    private View mOrderSummary;
    private TextView mTxtSubTotal;
    private TextView mTxtShippingFee;
    private TextView mTxtGST;
    private TextView mTxtGSTPrice;
    private TextView mTxtTotal;
    private TextView mTxtDiscount;
    private EditText mEdiPromotionCode;
    private TextView mTxtPromotionCode;
    private TextView mTxtCurrency;
    private LinearLayout mBtnApply;
    private LinearLayout mBtnCheckOut;
    private TextView mBtnShopping;
    private int mPosition = 0;
    private CartAdapter mAdapter;
    private CartItem mCartItem;
    private View mDivider;
    private TextView mTxtInfo;
    private CardView mCardViewInfo;
    private boolean mIsCart = false;
    private ConfirmationAdapter mConfirmationAdapter;
    private LinearLayout mLvPromotionApply;
    private View mDividerPromotionApply;
    private TextView mTxtCheckOut;
    private LinearLayout mLvDiscount;
    private LinearLayout mLvGST;
    private String TAG = CartConfirmationFragment.class.getSimpleName();
    private CheckOut mCheckOut = new CheckOut();

    public static CartConfirmationFragment newInstance(final boolean isCart) {
        CartConfirmationFragment frag = new CartConfirmationFragment();
        Bundle args = new Bundle();
        args.putBoolean("isCart", isCart);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mIsCart = getArguments().getBoolean("isCart");
        View view = inflater.inflate(R.layout.fragment_cart_confirmation, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mOrderSummary = view.findViewById(R.id.orderSummary);
        mCardViewInfo = (CardView) view.findViewById(R.id.cardViewInfo);
        mRecyclerViewOrderSummary = (RecyclerView) mOrderSummary.findViewById(R.id.recyclerView);
        mLvPromotionApply = (LinearLayout) mOrderSummary.findViewById(R.id.lvPromotionApply);
        mTxtDiscount = (TextView) mOrderSummary.findViewById(R.id.txtDiscount);
        mTxtPromotionCode = (TextView) mOrderSummary.findViewById(R.id.txtPromotionCode);
        mLvPromotionApply.setVisibility(View.GONE);
        mLvDiscount = (LinearLayout) mOrderSummary.findViewById(R.id.lvDiscount);
        mLvDiscount.setVisibility(View.GONE);
        mLvGST = (LinearLayout) mOrderSummary.findViewById(R.id.lvGST);
        mDividerPromotionApply = (View) mOrderSummary.findViewById(R.id.dividerPromotionApply);
        mDividerPromotionApply.setVisibility(View.GONE);
        mTxtSubTotal = (TextView) mOrderSummary.findViewById(R.id.txtSubTotal);
        mTxtShippingFee = (TextView) mOrderSummary.findViewById(R.id.txtShippingFee);
        mTxtGST = (TextView) mOrderSummary.findViewById(R.id.txtGST);
        mTxtGSTPrice = (TextView) mOrderSummary.findViewById(R.id.txtGSTPrice);
        mTxtTotal = (TextView) mOrderSummary.findViewById(R.id.txtTotal);
        mTxtInfo = (TextView) view.findViewById(R.id.txtInfo);
        mTxtCurrency = (TextView) mOrderSummary.findViewById(R.id.txtCurrency);
        mBtnApply = (LinearLayout) mOrderSummary.findViewById(R.id.btnApply);
        mBtnApply.setTag(false);
        mBtnCheckOut = (LinearLayout) mOrderSummary.findViewById(R.id.btnCheckOut);
        mBtnShopping = (TextView) mOrderSummary.findViewById(R.id.btnShopping);
        mTxtCheckOut = (TextView) mOrderSummary.findViewById(R.id.txtCheckOut);
        mEdiPromotionCode = (EditText) mOrderSummary.findViewById(R.id.ediPromotionCode);
        mDivider = mOrderSummary.findViewById(R.id.divider);
        mAdapter = new CartAdapter(getActivity(), mCartItem, TAG, mOrderSummary);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBtnApply.setOnClickListener(mBtnApplynOnClickListener);
        mBtnCheckOut.setOnClickListener(mBtnCheckOutOnClickListener);
        mBtnShopping.setOnClickListener(mBtnShoppingOnClickListener);
        mOrderSummary.setVisibility(View.GONE);
        if (mIsCart) {
            mCardViewInfo.setVisibility(View.GONE);
            mPosition = 0;
            Utilities.setSharePreference(null, "CheckOutFile", "CheckOutValue", getActivity());
        } else {
            mLvPromotionApply.setVisibility(View.GONE);
            mDividerPromotionApply.setVisibility(View.GONE);
            mCardViewInfo.setVisibility(View.VISIBLE);
            mDivider.setVisibility(View.VISIBLE);
            mRecyclerViewOrderSummary.setVisibility(View.VISIBLE);
            mConfirmationAdapter = new ConfirmationAdapter(getActivity(), null);
            mRecyclerViewOrderSummary.setAdapter(mConfirmationAdapter);
            mRecyclerViewOrderSummary.setLayoutManager(new LinearLayoutManager(getActivity()));
            mPosition = 3;
            mBtnCheckOut.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color_primary));
            mTxtCheckOut.setText(getString(R.string.place_order));
            mCheckOut = new Gson().fromJson(Utilities.getSharePreference("CheckOutFile", "CheckOutValue", getActivity()), CheckOut.class);
            String info = mCheckOut.getFirstName() + " " + mCheckOut.getLastName() + "\n\n" + mCheckOut.getAddress() + "\n\n" + mCheckOut.getEmail();
            mTxtInfo.setText(info);
        }
        return view;
    }

    private View.OnClickListener mBtnApplynOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mEdiPromotionCode.getText().toString().length() == 0) {
                Utilities.showError(getActivity(), "", getString(R.string.promotion_code_empty));
            } else {
                HttpHelper.postPromotionCode(getActivity(), CartActivity.class.getSimpleName(), mEdiPromotionCode.getText().toString());
            }
        }
    };

    private View.OnClickListener mBtnCheckOutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mIsCart) {
                mCheckOut.setGst(mTxtGST.getText().toString());
                mCheckOut.setGstPrice(mTxtGSTPrice.getText().toString());
                mCheckOut.setShippingFee(String.valueOf(mCartItem.getCarts().get(0).getShippingFee()));
                mCheckOut.setShippingType(String.valueOf(mCartItem.getCarts().get(0).getShippingType()));
                mCheckOut.setTotalPrice(String.valueOf(mCartItem.getCarts().get(0).getTotalPrice()));
                Utilities.setSharePreference(new Gson().toJson(mCheckOut), "CheckOutFile", "CheckOutValue", getActivity());
                Intent intent = new Intent(CartActivity.class.getSimpleName());
                intent.putExtra("isSlide", true);
                intent.putExtra("position", mPosition + 1);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
            } else {
                HttpHelper.postOrder(getActivity(), CartActivity.class.getSimpleName(), mCheckOut);
            }
        }
    };

    private View.OnClickListener mBtnShoppingOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CartActivity.class.getSimpleName());
            intent.putExtra("position", mPosition + 1);
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        }
    };

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(TAG)) {
                mCartItem = intent.getParcelableExtra("CartItem");
                if (mCartItem != null) {
                    if (mCartItem.getCarts() != null) {
                        if (mCartItem.getCarts().size() > 0) {
                            mOrderSummary.setVisibility(View.VISIBLE);
                            if (!mIsCart) {
                                mConfirmationAdapter.swapCartItem(mCartItem);
                            } else {
                                mAdapter.swapProduct(mCartItem);
                            }
                            mCheckOut.setTotalPrice(String.valueOf(mCartItem.getCarts().get(0).getTotalPrice()));
                            mCheckOut.setShippingFee(String.valueOf(mCartItem.getCarts().get(0).getShippingFee()));
                            mCheckOut.setTotalPriceIncGst(String.valueOf(mCartItem.getCarts().get(0).getTotalPriceIncGstPrice()));
                            mCheckOut.setPromotionalCode(String.valueOf(mCartItem.getCarts().get(0).getPromotionalCode()));
                            mCheckOut.setPromotionValue(String.valueOf(mCartItem.getCarts().get(0).getPromotionalValue()));
                            mCheckOut.setShippingType(mCartItem.getCarts().get(0).getShippingType());
                            mCheckOut.setGst(String.valueOf(mCartItem.getCarts().get(0).getGstText()));
                            mCheckOut.setGstPrice(String.valueOf(mCartItem.getCarts().get(0).getGstPrice()));
                            mCheckOut.setCurrency(CRUDHelper.getCountry(getActivity(), Utilities.getCountryID(getActivity())).getCurrency());

                            if (mCheckOut.getGst() == null) {
                                mLvGST.setVisibility(View.GONE);
                            } else {
                                mLvGST.setVisibility(View.VISIBLE);
                            }

                            mTxtSubTotal.setText(mCheckOut.getCurrency() + mCheckOut.getTotalPrice());
                            mTxtShippingFee.setText(mCheckOut.getCurrency() + mCheckOut.getShippingFee());
                            String gstText = getString(R.string.gsts) + " " + mCheckOut.getGst() + "%";
                            if (mCheckOut.getGst().contains(".")) {
                                String[] result = mCheckOut.getGst().split(".");
                                if (result.length != 0) {
                                    if (Integer.valueOf(result[result.length - 1]) == 0) {
                                        gstText = getString(R.string.gsts) + " " + result[0] + "%";
                                    }
                                }
                            }
                            mTxtGST.setText(gstText);
                            mTxtGSTPrice.setText(mCheckOut.getCurrency() + " " + mCheckOut.getGstPrice());
                            mTxtCurrency.setText(mCheckOut.getCurrency());
                            mTxtTotal.setText(mCheckOut.getTotalPriceIncGst());

                            if (mCheckOut.getPromotionalCode().length() != 0) {
                                mLvDiscount.setVisibility(View.VISIBLE);
                                mLvPromotionApply.setVisibility(View.GONE);
                                mDividerPromotionApply.setVisibility(View.GONE);
                                mTxtDiscount.setText(mCheckOut.getCurrency() + String.valueOf(mCartItem.getCarts().get(0).getPromotionalValue()));
                                mTxtPromotionCode.setText(getString(R.string.promotion_code) + " " + mCheckOut.getPromotionalCode());
                            } else {
                                mLvDiscount.setVisibility(View.GONE);
                                if (!mIsCart) {
                                    mLvPromotionApply.setVisibility(View.GONE);
                                    mDividerPromotionApply.setVisibility(View.GONE);
                                } else {
                                    mLvPromotionApply.setVisibility(View.VISIBLE);
                                    mDividerPromotionApply.setVisibility(View.VISIBLE);
                                }
                            }
                            String info = mCheckOut.getFirstName() + " " + mCheckOut.getLastName() + "\n\n" + mCheckOut.getAddress() + "\n\n" + mCheckOut.getEmail();
                            if (mCheckOut.getFirstName() != null) {
                                mTxtInfo.setText(info);
                            }
                        } else {
                            mOrderSummary.setVisibility(View.GONE);
                            Utilities.showError(getActivity(), "", getString(R.string.no_items));
                        }
                    } else {
                        mOrderSummary.setVisibility(View.GONE);
                        Utilities.showError(getActivity(), "", getString(R.string.no_items));
                    }
                } else {
                    mOrderSummary.setVisibility(View.GONE);
                    Utilities.showError(getActivity(), "", getString(R.string.no_items));
                }
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        TAG += mPosition;
        mAdapter.setTAG(TAG);
        com.applab.goodmorning.Register.webapi.HttpHelper.getCountry(getActivity(), TAG);
        IntentFilter iff = new IntentFilter(TAG);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, iff);
        if (mIsCart) {
            Account account = com.applab.goodmorning.Account.database.CRUDHelper.getAccount(getActivity());
            HttpHelper.getCart(getActivity(), TAG, account.getDefStateCode(), String.valueOf(account.getDefCountryId()));
        } else {
            HttpHelper.getCart(getActivity(), TAG, mCheckOut.getStateCode(), mCheckOut.getCountryId());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }
}
