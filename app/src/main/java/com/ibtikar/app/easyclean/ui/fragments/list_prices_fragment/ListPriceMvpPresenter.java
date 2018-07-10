package com.ibtikar.app.easyclean.ui.fragments.list_prices_fragment;

import com.ibtikar.app.easyclean.ui.activities.base.MvpPresenter;

public interface ListPriceMvpPresenter <V extends ListPriceMvpView> extends MvpPresenter<V> {
    void getListPrices(String cleanerId , String CategoryId);
}
