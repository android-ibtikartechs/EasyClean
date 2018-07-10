package com.ibtikar.app.easyclean.ui.fragments.pricing;

import com.ibtikar.app.easyclean.ui.activities.base.MvpView;

public interface PricingMvpView extends MvpView {
    void addCategoryTab(String title,int tabIndex);
    void addCategoryFragment(String id);
}
