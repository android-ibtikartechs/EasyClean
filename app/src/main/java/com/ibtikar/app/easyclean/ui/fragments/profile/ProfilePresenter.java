package com.ibtikar.app.easyclean.ui.fragments.profile;

import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.ui.activities.base.BasePresenter;

public class ProfilePresenter <V extends ProfileMvpView> extends BasePresenter<V> implements ProfileMvpPresenter<V> {

    public ProfilePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void checkIsLogin() {
        if (getDataManager().isLogIn())
            getMvpView().signout();

        else
            getMvpView().intentToRegesteration();
    }

    @Override
    public void signout() {
        getDataManager().setLogin(false);
    }

    @Override
    public boolean justCheckLogin() {
        return getDataManager().isLogIn();
    }
}
