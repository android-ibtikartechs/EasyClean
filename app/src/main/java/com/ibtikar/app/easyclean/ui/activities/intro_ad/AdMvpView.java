package com.ibtikar.app.easyclean.ui.activities.intro_ad;

import com.ibtikar.app.easyclean.ui.activities.base.MvpView;

public interface AdMvpView extends MvpView {
    void startCountDown();
    void setBannerView(String imgUrl);
    void hideProgressBar();
}
