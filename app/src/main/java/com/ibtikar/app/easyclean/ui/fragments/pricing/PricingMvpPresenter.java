package com.ibtikar.app.easyclean.ui.fragments.pricing;

import com.ibtikar.app.easyclean.ui.activities.base.MvpPresenter;

public interface PricingMvpPresenter <V extends PricingMvpView> extends MvpPresenter<V> {
    void getCategories(String id);
}
