package com.ibtikar.app.easyclean.ui.fragments.home;

import com.ibtikar.app.easyclean.data.models.City;
import com.ibtikar.app.easyclean.ui.activities.base.MvpPresenter;

import java.util.ArrayList;

public interface HomeMvpPresenter <V extends HomeMvpView> extends MvpPresenter<V> {
    void loadFirstPage();
    void loadNextPage(Integer page);

    void loadCities();
    void search(String city);

}
