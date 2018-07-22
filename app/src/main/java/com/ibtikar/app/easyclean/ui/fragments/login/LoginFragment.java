package com.ibtikar.app.easyclean.ui.fragments.login;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ibtikar.app.easyclean.MvpApp;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.ui.activities.forgetpassword.ForgetPasswordActivity;
import com.ibtikar.app.easyclean.ui.fragments.base.BaseFragment;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseFragment implements LoginMvpView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.editText)
    EditText etEmail;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.lout_main_login)
    ConstraintLayout loutMain;

    @BindView(R.id.button)
    Button btnLogin;


    @BindView(R.id.lout_btn_login_facebook)
    CardView btnLoginFacebook;

    @BindView(R.id.lout_btn_login_google)
    CardView btnLoginGoogle;

    @BindView(R.id.lout_btn_login_twitter)
    CardView btnLoginTwitter;

    @BindView(R.id.textView2)
    TextView btnForgetPassword;

    LoginPresenter presenter;

    Handler handler;


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        handler = new Handler(Looper.getMainLooper());
        DataManager dataManager = ((MvpApp) getActivity().getApplication()).getDataManager();
        presenter = new LoginPresenter(dataManager);
        presenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(etEmail.getText().toString(),etPassword.getText().toString());
            }
        });

        btnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ForgetPasswordActivity.class));
            }
        });
    }

    @Override
    public void showToast(final String msg, int flag_text_or_int) {
        if (flag_text_or_int == StaticValues.FLAG_TOAST_INT)
            Toast.makeText(getActivity(), Integer.valueOf(msg), Toast.LENGTH_SHORT).show();
        else
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public void finishRegestration() {
        getActivity().finish();
    }

    @Override
    public void showReActivateSnackbar(String msg) {
        Snackbar snackbar = Snackbar
                .make(loutMain, msg, Snackbar.LENGTH_LONG)
                .setAction("إعادة الإرسال", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.resendActivation(etEmail.getText().toString());
                    }
                });

        snackbar.show();
    }

    @Override
    public void showActivationSentSnackBar() {
        Snackbar snackbar1 = Snackbar.make(loutMain, "تم إرسال رابط التفعيل على بريك الإلكتروني", Snackbar.LENGTH_SHORT);
        snackbar1.show();
    }
}
