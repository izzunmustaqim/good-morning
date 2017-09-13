package com.applab.goodmorning.Checkout.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.applab.goodmorning.Checkout.fragment.CartConfirmationFragment;
import com.applab.goodmorning.Checkout.fragment.DeliveryPaymentFragment;

/**
 * Created by user on 13/4/2016.
 */
public class CheckOutPagerAdapter extends FragmentStatePagerAdapter {

    private Activity mActivity;
    private int mTotal = 0;

    // constructor
    public CheckOutPagerAdapter(FragmentManager fm, Activity activity, int total) {
        super(fm);
        mActivity = activity;
        mTotal = total;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {//Cart
            return CartConfirmationFragment.newInstance(true);
        } else if (position == 2) {//Payment
            return DeliveryPaymentFragment.newInstance(false);
        } else if (position == 1) {//Delivery
            return DeliveryPaymentFragment.newInstance(true);
        } else if (position == 3) {//Confirmation
            return CartConfirmationFragment.newInstance(false);
        } else {
            return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mTotal;
    }
}
