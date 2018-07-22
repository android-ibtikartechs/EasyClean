package com.ibtikar.app.easyclean.ui.activities.forgetpassword;

import com.ibtikar.app.easyclean.ui.activities.base.MvpView;

public interface ForgetPasswordMvpView extends MvpView {
    void showToast(String msg, int flag_text_or_int);
    void finishSend();
}
