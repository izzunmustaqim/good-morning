package com.applab.goodmorning.Language.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applab.goodmorning.Language.holder.LanguageHolder;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Settings.model.Settings;
import com.applab.goodmorning.Utilities.Utilities;

import java.util.ArrayList;

/**
 * Created by user on 09-Mar-16.
 */
public class LanguageAdapter extends RecyclerView.Adapter<LanguageHolder> {
    private Context mContext;
    private ArrayList<Settings> mSettings = new ArrayList<>();
    private LayoutInflater mInflater;
    String languageToLoad = "en_US";
    String languageToLoadMs = "ms";
    String languageToLoadZh = "zh";

    public LanguageAdapter(final Context context, ArrayList<Settings> settingses) {
        mContext = context;
        mSettings = settingses;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public LanguageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.custom_language_row, parent, false);
        return new LanguageHolder(v);
    }

    @Override
    public void onBindViewHolder(LanguageHolder holder, final int position) {
        final LanguageHolder languageHolder = (LanguageHolder) holder;
        languageHolder.getTxtLanguage().setText(mSettings.get(position).getItemSelected());
        if (position == 0) {
            if (!getLanguage().equals(null)) {
                if (getLanguage().equals(languageToLoad)) {
                    holder.getImgCheck().setVisibility(View.VISIBLE);
                }
            } else {
                holder.getImgCheck().setVisibility(View.VISIBLE);
            }
        } else if (position == 1) {
            if (!getLanguage().equals(null)) {
                if (getLanguage().equals(languageToLoadMs)) {
                    holder.getImgCheck().setVisibility(View.VISIBLE);
                }
            } else {
                holder.getImgCheck().setVisibility(View.VISIBLE);
            }
        } else if (position == 2) {
            if (!getLanguage().equals(null)) {
                if (getLanguage().equals(languageToLoadZh)) {
                    holder.getImgCheck().setVisibility(View.VISIBLE);
                }
            } else {
                holder.getImgCheck().setVisibility(View.VISIBLE);
            }
        }
        languageHolder.getBtnLanguage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        Utilities.setLocale(languageToLoad, mContext);
                        Utilities.saveLanguage(languageToLoad, mContext);
                        break;
                    case 1:
                        Utilities.setLocale(languageToLoadMs, mContext);
                        Utilities.saveLanguage(languageToLoadMs, mContext);
                        break;
                    case 2:
                        Utilities.setLocale(languageToLoadZh, mContext);
                        Utilities.saveLanguage(languageToLoadZh, mContext);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSettings.size();
    }

    public String getLanguage() {
        String langPref = "Language";
        SharedPreferences preferences = mContext.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = preferences.getString(langPref, "");
        return language;
    }
}
