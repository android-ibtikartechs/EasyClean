package com.ibtikar.app.easyclean.ui.activities.cleaners_details;

import com.ibtikar.app.easyclean.data.models.CleanerDetails;
import com.ibtikar.app.easyclean.ui.activities.base.MvpView;

public interface CleanerDetailsMvpView extends MvpView {
    void populateMainData(CleanerDetails cleanerDetails);
    void hideErrorView();
    void showErrorView();
    String fetchErrorMessage();
    void showProgressBar();
}
