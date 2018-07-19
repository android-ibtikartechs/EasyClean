package com.ibtikar.app.easyclean.ui.fragments.login;

import com.ibtikar.app.easyclean.ui.activities.base.MvpView;

public interface LoginMvpView extends MvpView {
    void showToast(String msg, int flag_text_or_int);
    void finishRegestration();
    void showReActivateSnackbar(String msg);
    void showActivationSentSnackBar();
}
