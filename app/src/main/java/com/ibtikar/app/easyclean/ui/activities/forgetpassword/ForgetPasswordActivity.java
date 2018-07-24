package com.ibtikar.app.easyclean.ui.activities.forgetpassword;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ibtikar.app.easyclean.MvpApp;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.ui.activities.base.BaseActivity;
import com.ibtikar.app.easyclean.ui.fragments.login.LoginPresenter;
import com.ibtikar.app.easyclean.ui_utilities.CustomFontTextView;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordMvpView {

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

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
        setupActionBar("استعادة كلمة المرور");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendPassword(etEmail.getText().toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
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



    public void setupActionBar(String title) {
        changeLang(ForgetPasswordActivity.this,"ar");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_act);

        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.custom_action_bar_title, null);

        ((CustomFontTextView)v.findViewById(R.id.title)).setText(title);

        actionBar.setCustomView(v);
    }



}
