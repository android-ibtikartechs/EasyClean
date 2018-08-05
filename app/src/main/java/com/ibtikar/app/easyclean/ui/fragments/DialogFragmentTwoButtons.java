package com.ibtikar.app.easyclean.ui.fragments;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibtikar.app.easyclean.R;

import butterknife.BindView;

public class DialogFragmentTwoButtons extends DialogFragment {
    @BindView(R.id.imageView5)
    ImageView mainIcon;

    @BindView(R.id.textView5)
    TextView title;


    public static DialogFragmentTwoButtons newInstance() {
        DialogFragmentTwoButtons fragment = new DialogFragmentTwoButtons();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.two_bottons_dialog_fragment, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
        return rootView;
    }
}
