package com.ibtikar.app.easyclean.ui.fragments.login;

import com.ibtikar.app.easyclean.ui.activities.base.MvpPresenter;

public interface LoginMvpPresenter <V extends LoginMvpView> extends MvpPresenter<V> {
    void login(String email, String password);
    void resendActivation(String email);
}
