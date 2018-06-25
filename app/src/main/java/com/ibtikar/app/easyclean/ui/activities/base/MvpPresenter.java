package com.ibtikar.app.easyclean.ui.activities.base;

public interface MvpPresenter <V extends MvpView> {
    void onAttach(V mvpView);
}
