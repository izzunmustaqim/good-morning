package com.applab.goodmorning.Utilities.dialog;

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
 * Created by user on 12/4/2016.
 */
public class GeneralDialogFragment extends DialogFragment {
    private TextView mTxtTitle, mTxtMessage;
    private ImageView mBtnCancel;
    private LinearLayout mBtnOk;
    private String mMessage, mTitle;
    private static AlertDialog.Builder builder;
    private static Context mContext;
    private String TAG = GeneralDialogFragment.class.getSimpleName();

    /**
     * Public factory method to create new RenameFileDialogFragment instances.
     *
     * @return Dialog ready to show.
     */
    public static GeneralDialogFragment newInstance(final Context context, final String message, final String title) {
        GeneralDialogFragment frag = new GeneralDialogFragment();
        Bundle args = new Bundle();
        args.putString("message", message);
        args.putString("title", title);
        frag.setArguments(args);
        builder = new AlertDialog.Builder(context);
        mContext = context;
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mMessage = getArguments().getString("message");
        mTitle = getArguments().getString("title");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.window_general_dialog, null);
        mTxtTitle = (TextView) view.findViewById(R.id.txtTitle);
        mTxtMessage = (TextView) view.findViewById(R.id.txtMessage);
        mTxtMessage.setText(mMessage);
        mTxtTitle.setText(mTitle);
        mBtnCancel = (ImageView) view.findViewById(R.id.btnCancel);
        mBtnOk = (LinearLayout) view.findViewById(R.id.btnOk);

        mBtnCancel.setOnClickListener(btnCancelOnClickListener);
        mBtnOk.setOnClickListener(btnOkOnClickListener);

        //Build the dialog
        builder.setView(view);
        Dialog d = builder.create();
        d.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return d;
    }

    private View.OnClickListener btnCancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GeneralDialogFragment.this.getDialog().cancel();
        }
    };

    private View.OnClickListener btnOkOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GeneralDialogFragment.this.getDialog().cancel();
        }
    };
}
