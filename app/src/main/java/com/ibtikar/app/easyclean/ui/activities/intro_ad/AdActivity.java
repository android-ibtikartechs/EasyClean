package com.ibtikar.app.easyclean.ui.activities.intro_ad;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.ibtikar.app.easyclean.MvpApp;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.ui.activities.base.BaseActivity;
import com.ibtikar.app.easyclean.ui.activities.main.MainActivity;
import com.ibtikar.app.easyclean.ui_utilities.CustomFontTextView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdActivity extends BaseActivity implements AdMvpView {

    @BindView(R.id.imageView)
    ImageView imAd;

    @BindView(R.id.progressBar1)
    ProgressBar countDownProgBar;

    @BindView(R.id.tv_btn_skip)
    CustomFontTextView btnSkipAd;

    @BindView(R.id.spin_kit)
    ProgressBar loadProgBar;

    int total = 0;
    private CountDownTimer cdt;
    private int timePassed;
    Handler handler;
    float density;

    private boolean skipped;
    AdPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        handler = new Handler(Looper.getMainLooper());
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new AdPresenter(dataManager);
        presenter.onAttach(this);


        btnSkipAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cdt !=null)
                    cdt.cancel();
                skipped = true;
                startActivity(new Intent(AdActivity.this, MainActivity.class));
                finish();
            }
        });

      /*  if (loadProgBar != null) {
            loadProgBar.setIndeterminate(true);
            loadProgBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white_blue), android.graphics.PorterDuff.Mode.MULTIPLY);
        } */


        CubeGrid doubleBounce = new CubeGrid();
        loadProgBar.setIndeterminateDrawable(doubleBounce);
        density = getResources().getDisplayMetrics().density;

        presenter.getBannerAd(density);
    }

    private void changeStatusBarColor() {
    }


    @Override
    public void startCountDown() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                countDownProgBar.setProgress(0);


                final int oneMin= 5 * 1000; // 1 minute in milli seconds

                // CountDownTimer starts with 1 minutes and every onTick is 1 second
                cdt = new CountDownTimer(oneMin, 30) {

                    public void onTick(final long millisUntilFinished) {

                        total++;
                        // handler.post(new Runnable() {
                        //     @Override
                        //   public void run() {
                        countDownProgBar.setProgress((int)total*100/(oneMin/30));
                        //   }
                        // });


                    }

                    public void onFinish() {
                        // DO something when 1 minute is up
                        total++;
                        // handler.post(new Runnable() {
                        // @Override
                        // public void run() {
                        countDownProgBar.setProgress(100);
                        // }
                        // });
                        if (!skipped) {
                            startActivity(new Intent(AdActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                }.start();

            }
        });

    }

    @Override
    public void setBannerView(final String imgUrl) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (imgUrl.equals("")|| imgUrl == null)
                {}
                else
                    Picasso.get()
                            .load(imgUrl)
                            .into(imAd);
            }
        });
    }

    @Override
    public void hideProgressBar() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (loadProgBar!=null)
                    loadProgBar.setVisibility(View.GONE);
            }
        });
    }
}
