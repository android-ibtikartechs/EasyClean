package com.ibtikar.app.easyclean.ui.activities.cleaners_details;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.responses.CleanerDetailsResponse;
import com.ibtikar.app.easyclean.ui.activities.base.BasePresenter;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class CleanerDetailsPresenter <V extends CleanerDetailsMvpView> extends BasePresenter<V> implements CleanerDetailsMvpPresenter<V> {

    public CleanerDetailsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getMainDetails(String cleanerId) {
        getMvpView().showProgressBar();

        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(buildUrl(cleanerId))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getMvpView().showErrorView();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String stringResponse = response.body().string();
                Log.d("Tag", "onResponse: " + stringResponse);
                CleanerDetailsResponse cleanerDetailsResponse = new Gson().fromJson(stringResponse, CleanerDetailsResponse.class);
                if (cleanerDetailsResponse.isStatus())
                {
                    getMvpView().populateMainData(cleanerDetailsResponse.getDetails());
                }

            }
        });
    }

    private String buildUrl(String id) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(StaticValues.URL_AUOTHORITY)
                .appendPath("mob")
                .appendPath("cleaner_details")
                .appendPath(id);
        String url = builder.build().toString();
        return url;
    }
}
