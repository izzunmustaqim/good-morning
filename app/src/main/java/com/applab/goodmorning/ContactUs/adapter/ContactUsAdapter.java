package com.applab.goodmorning.ContactUs.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.applab.goodmorning.ContactUs.holder.ContactUsHolder;
import com.applab.goodmorning.ContactUs.model.ContactUsItem;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;

/**
 * Created by user on 7/4/2016.
 */
public class ContactUsAdapter extends RecyclerView.Adapter<ContactUsHolder> {
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ContactUsItem mContactUsItem;

    public ContactUsAdapter(Context context, ContactUsItem contactUsItem) {
        mContactUsItem = contactUsItem;
        mContext = context;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ContactUsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_contact_us, parent, false);
        return new ContactUsHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactUsHolder holder, int position) {
        holder.getmTxtBranchName().setText(mContactUsItem.getContactUss().get(position).getOutletTitle());
        holder.getmTxtCompanyName().setText(mContactUsItem.getContactUss().get(position).getOutletSubTitle());
        holder.getmTxtLocation().setText(mContactUsItem.getContactUss().get(position).getAddress());
        holder.getmTxtTelNo().setText(mContactUsItem.getContactUss().get(position).getTelNo());
        holder.getmTxtMobileNo().setText(mContactUsItem.getContactUss().get(position).getMobileNo());
        holder.getmTxtCareline().setText(mContactUsItem.getContactUss().get(position).getCareline());
        holder.getmTxtFaxNo().setText(mContactUsItem.getContactUss().get(position).getFax());
        holder.getmTxtEmail().setText(mContactUsItem.getContactUss().get(position).getEmail());
        holder.getmTxtUrl().setText(mContactUsItem.getContactUss().get(position).getWebsite());
        holder.getmTxtOperationHour().setText(mContactUsItem.getContactUss().get(position).getOperationHour());
        holder.getmBtnGoogleMap().setTag(mContactUsItem.getContactUss().get(position).getLatitude() + "/" + mContactUsItem.getContactUss().get(position).getLongitude());
        holder.getmBtnWaze().setTag(mContactUsItem.getContactUss().get(position).getLatitude() + "/" + mContactUsItem.getContactUss().get(position).getLongitude());
        holder.getmBtnGoogleMap().setOnClickListener(mBtnGoogleMapOnClickListener);
        holder.getmBtnWaze().setOnClickListener(mBtnWazeOnClickListener);
    }

    private View.OnClickListener mBtnGoogleMapOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LinearLayout btnGoogleMap = (LinearLayout) v.findViewById(R.id.btnGoogleMap);
            String[] result = btnGoogleMap.getTag().toString().split("/");
            Utilities.openGoogleMap(mContext, result[0], result[1]);
        }
    };

    private View.OnClickListener mBtnWazeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LinearLayout btnGoogleMap = (LinearLayout) v.findViewById(R.id.btnWaze);
            String[] result = btnGoogleMap.getTag().toString().split("/");
            Utilities.openWaze(mContext, result[0], result[1]);
        }
    };

    @Override
    public int getItemCount() {
        return mContactUsItem == null ? 0 : mContactUsItem.getContactUss().size();
    }

    public void swapContactUs(ContactUsItem contactUsItem) {
        this.mContactUsItem = contactUsItem;
        this.notifyDataSetChanged();
    }
}
