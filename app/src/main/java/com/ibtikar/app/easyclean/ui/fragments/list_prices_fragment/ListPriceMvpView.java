package com.ibtikar.app.easyclean.ui.fragments.list_prices_fragment;

import com.ibtikar.app.easyclean.data.models.PricingItem;
import com.ibtikar.app.easyclean.ui.activities.base.MvpView;

import java.util.ArrayList;

public interface ListPriceMvpView extends MvpView {
    void setupPriceList (ArrayList<PricingItem> categoriesList);

}
