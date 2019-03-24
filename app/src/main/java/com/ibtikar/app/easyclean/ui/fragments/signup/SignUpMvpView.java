package com.ibtikar.app.easyclean.ui.fragments.signup;

import com.ibtikar.app.easyclean.data.models.City;
import com.ibtikar.app.easyclean.data.models.CleanerItemModel;
import com.ibtikar.app.easyclean.data.models.Destrict;
import com.ibtikar.app.easyclean.ui.activities.base.MvpView;

import java.util.ArrayList;
import java.util.Collection;

public interface SignUpMvpView extends MvpView {
    void showToast(String msg, int flag_text_or_int);
    void setupCitiesSpinner(ArrayList<City> objects);
    void setupdestrictsSpinner(ArrayList<Destrict> objects);
    void showReActivateSnackbar(String msg);
    void showActivationSentSnackBar();
    void showSnackbarLogin();
    void finishRegisteration();
}
