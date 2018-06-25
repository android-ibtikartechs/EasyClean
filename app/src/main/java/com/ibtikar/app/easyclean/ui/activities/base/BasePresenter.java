package com.ibtikar.app.easyclean.ui.activities.base;

import com.ibtikar.app.easyclean.data.DataManager;

public class BasePresenter <V extends MvpView> implements MvpPresenter<V> {
    private V mMvpView;
    DataManager mDataManager;

    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }
}
