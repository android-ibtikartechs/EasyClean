package com.ibtikar.app.easyclean.ui.activities.forgetpassword;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ibtikar.app.easyclean.MvpApp;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.ui.activities.base.BaseActivity;
import com.ibtikar.app.easyclean.ui.fragments.login.LoginPresenter;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordMvpView {

    @BindView(R.id.toolbar_main)
    Toolbar mainToolBar;

    @BindView(R.id.editText)
    EditText etEmail;

    @BindView(R.id.button)
    Button btnSend;

    Handler handler;
    ForgetPasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        handler = new Handler(Looper.getMainLooper());
        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new ForgetPasswordPresenter(dataManager);
        presenter.onAttach(this);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendPassword(etEmail.getText().toString());
            }
        });

    }

    @Override
    public void showToast(final String msg, final int flag_text_or_int) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (flag_text_or_int == StaticValues.FLAG_TOAST_INT)
                    Toast.makeText(ForgetPasswordActivity.this, Integer.valueOf(msg), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ForgetPasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void finishSend() {
        finish();
    }
}
