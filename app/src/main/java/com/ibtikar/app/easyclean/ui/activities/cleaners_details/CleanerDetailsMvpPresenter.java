package com.ibtikar.app.easyclean.ui.activities.cleaners_details;

import com.ibtikar.app.easyclean.ui.activities.base.MvpPresenter;

public interface CleanerDetailsMvpPresenter <V extends CleanerDetailsMvpView> extends MvpPresenter<V> {
    void getMainDetails(String cleanerId);
}
