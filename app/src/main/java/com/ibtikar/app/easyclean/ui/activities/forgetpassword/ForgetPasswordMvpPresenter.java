package com.ibtikar.app.easyclean.ui.activities.forgetpassword;

import com.ibtikar.app.easyclean.ui.activities.base.MvpPresenter;

public interface ForgetPasswordMvpPresenter <V extends ForgetPasswordMvpView> extends MvpPresenter<V> {
    void sendPassword(String email);
}
