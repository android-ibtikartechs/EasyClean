package com.ibtikar.app.easyclean.ui.fragments.home;

import com.ibtikar.app.easyclean.ui.activities.base.MvpPresenter;

public interface HomeMvpPresenter <V extends HomeMvpView> extends MvpPresenter<V> {
    void loadFirstPage();
    void loadNextPage(Integer page);
}
