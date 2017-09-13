package com.applab.goodmorning.News.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.applab.goodmorning.R;


/**
 * Created by User on 5/4/2016.
 */
public class NewsDetailsTextHolder extends RecyclerView.ViewHolder {
    private TextView txtDate;
    private TextView txtBottomDate;
    private TextView txtTitle;
    private TextView txtDescription;

    public NewsDetailsTextHolder(View itemView) {
        super(itemView);
        txtDate = (TextView) itemView.findViewById(R.id.txtDate);
        txtBottomDate = (TextView) itemView.findViewById(R.id.txtBottomDate);
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitleNews);
        txtDescription = (TextView) itemView.findViewById(R.id.txtDesc);
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtBottomDate() {
        return txtBottomDate;
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public TextView getTxtDescription() {
        return txtDescription;
    }
}
