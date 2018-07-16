package com.ibtikar.app.easyclean.ui.activities.intro_ad;

import com.ibtikar.app.easyclean.ui.activities.base.MvpPresenter;

public interface AdMvpPresenter <V extends AdMvpView> extends MvpPresenter<V> {
    void getBannerAd(float density);
}
