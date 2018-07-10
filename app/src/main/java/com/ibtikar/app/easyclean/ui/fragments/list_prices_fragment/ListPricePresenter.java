package com.ibtikar.app.easyclean.ui.fragments.list_prices_fragment;

import android.net.Uri;

import com.google.gson.Gson;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.models.PricingItem;
import com.ibtikar.app.easyclean.data.responses.pricesListResponse;
import com.ibtikar.app.easyclean.ui.activities.base.BasePresenter;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ListPricePresenter <V extends ListPriceMvpView> extends BasePresenter<V> implements ListPriceMvpPresenter<V> {
    public ListPricePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getListPrices(String cleanerId, String CategoryId) {
        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(buildUrl(CategoryId, cleanerId))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ArrayList<PricingItem> list = new ArrayList<>();
                String stringResponse = response.body().string();
                pricesListResponse pricesListResponse = new Gson().fromJson(stringResponse, pricesListResponse.class);

                if (pricesListResponse.getStatus().equals("OK"))
                {
                    list = pricesListResponse.getDetails().getItems();
                }
                getMvpView().setupPriceList(list);
            }
        });
    }

    private String buildUrl(String categId, String cleanerId) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(StaticValues.URL_AUOTHORITY)
                .appendPath("mob")
                .appendPath("cleaner_itesms")
                .appendPath(cleanerId)
                .appendPath(categId);
        String url = builder.build().toString();
        return url;
    }
}
