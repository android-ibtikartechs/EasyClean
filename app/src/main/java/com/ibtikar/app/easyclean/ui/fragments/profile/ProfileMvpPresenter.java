package com.ibtikar.app.easyclean.ui.fragments.profile;

import com.ibtikar.app.easyclean.ui.activities.base.MvpPresenter;

public interface ProfileMvpPresenter <V extends ProfileMvpView> extends MvpPresenter<V> {
    void checkIsLogin();
    void signout();
    boolean justCheckLogin();
}
