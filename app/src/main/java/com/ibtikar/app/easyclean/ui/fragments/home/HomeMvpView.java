package com.ibtikar.app.easyclean.ui.fragments.home;

import com.ibtikar.app.easyclean.data.models.CleanerItemModel;
import com.ibtikar.app.easyclean.ui.activities.base.MvpView;

import java.util.ArrayList;

public interface HomeMvpView extends MvpView {

    void hideErrorView();
    void showErrorView();
    String fetchErrorMessage();
    void showProgressBar();
    void setLastPageTrue();
    void addMoreToAdapter(final ArrayList<CleanerItemModel> list);
    void addLoadingFooter();
    void removeLoadingFooter();
    void showRetryAdapter();
    void setIsLoadingFalse();
}
