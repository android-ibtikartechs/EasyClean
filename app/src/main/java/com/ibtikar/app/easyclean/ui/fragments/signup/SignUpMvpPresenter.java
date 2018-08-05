package com.ibtikar.app.easyclean.ui.fragments.signup;

import com.ibtikar.app.easyclean.ui.activities.base.MvpPresenter;

import java.util.ArrayList;

public interface SignUpMvpPresenter <V extends SignUpMvpView> extends MvpPresenter<V> {
    void signup(ArrayList<String> data);
    void signupSocial(String socialType, String accessToken);
    void loadCities();
    void loadDestricts(String cityId);
    void resendActivation(String email);
}
