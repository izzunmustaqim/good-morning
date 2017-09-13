package com.applab.goodmorning.Language.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applab.goodmorning.R;

/**
 * Created by user on 09-Mar-16.
 */
public class LanguageHolder extends RecyclerView.ViewHolder {
    private TextView txtLanguage;
    private LinearLayout btnLanguage;
    private RelativeLayout imgCheck;

    public LanguageHolder(View itemView) {
        super(itemView);
        txtLanguage = (TextView) itemView.findViewById(R.id.txtLanguage);
        imgCheck = (RelativeLayout) itemView.findViewById(R.id.imgCheck);
        btnLanguage = (LinearLayout) itemView.findViewById(R.id.btnLanguage);
    }

    public TextView getTxtLanguage() {
        return txtLanguage;
    }

    public RelativeLayout getImgCheck() {
        return imgCheck;
    }

    public LinearLayout getBtnLanguage() {
        return btnLanguage;
    }

}
