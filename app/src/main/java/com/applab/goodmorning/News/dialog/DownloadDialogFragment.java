package com.applab.goodmorning.News.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;

/**
 * Created by User on 5/4/2016.
 */
public class DownloadDialogFragment extends DialogFragment {
    private String mUrl, mFolderName;
    private TextView mTxtTitle, mTxtMessage;
    private ImageView mBtnCancel;
    private LinearLayout mBtnYes, mBtnNo;
    private static AlertDialog.Builder builder;
    private static Context mContext;
    private String TAG = DownloadDialogFragment.class.getSimpleName();

    /**
     * Public factory method to create new RenameFileDialogFragment instances.
     *
     * @return Dialog ready to show.
     */
    public static DownloadDialogFragment newInstance(final Context context, final String url, String folderName) {
        DownloadDialogFragment frag = new DownloadDialogFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("folderName", folderName);
        frag.setArguments(args);
        builder = new AlertDialog.Builder(context);
        mContext = context;
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mUrl = getArguments().getString("url");
        mFolderName = getArguments().getString("folderName");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.window_dailog, null);
        mTxtTitle = (TextView) view.findViewById(R.id.txtTitle);
        mTxtMessage = (TextView) view.findViewById(R.id.txtMessage);
        String path = getResources().getString(R.string.download_message) + " " + getResources().getString(R.string.folder_name) + "/" + mFolderName;
        mTxtMessage.setText(path);
        mTxtTitle.setText(getResources().getString(R.string.download));
        mBtnCancel = (ImageView) view.findViewById(R.id.btnCancel);
        mBtnYes = (LinearLayout) view.findViewById(R.id.btnYes);
        mBtnNo = (LinearLayout) view.findViewById(R.id.btnNo);

        mBtnCancel.setOnClickListener(btnCancelOnClickListener);
        mBtnYes.setOnClickListener(btnYesOnClickListener);
        mBtnNo.setOnClickListener(btnNoOnClickListener);

        //Build the dialog
        builder.setView(view);
        Dialog d = builder.create();
        d.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return d;
    }

    private View.OnClickListener btnCancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DownloadDialogFragment.this.getDialog().cancel();
        }
    };

    private View.OnClickListener btnYesOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utilities.downloadFile(mUrl, mContext, mFolderName);
            DownloadDialogFragment.this.getDialog().cancel();
        }
    };

    private View.OnClickListener btnNoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DownloadDialogFragment.this.getDialog().cancel();
        }
    };
}
