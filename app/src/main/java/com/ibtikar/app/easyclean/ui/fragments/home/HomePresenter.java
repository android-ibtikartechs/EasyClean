package com.ibtikar.app.easyclean.ui.fragments.home;

import android.net.Uri;

import com.google.gson.Gson;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.HomeResponse;
import com.ibtikar.app.easyclean.ui.activities.base.BasePresenter;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class HomePresenter <V extends HomeMvpView> extends BasePresenter<V> implements HomeMvpPresenter<V>{


    public HomePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadFirstPage() {
        getMvpView().showProgressBar();
        getMvpView().setLastPageTrue();

        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(buildUrl())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getMvpView().showErrorView();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String stringResponse = response.body().string();
                HomeResponse homeResponse = new Gson().fromJson(stringResponse, HomeResponse.class);

                if (homeResponse.isStatus())
                {
                    getMvpView().addMoreToAdapter(homeResponse.getList());
                    getMvpView().hideErrorView();
                }

                else
                    getMvpView().showErrorView();

            }
        });

    }

    @Override
    public void loadNextPage(Integer page) {

    }


    private String buildUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(StaticValues.URL_AUOTHORITY)
                .appendPath("mob")
                .appendPath("getallcleaners");
        String url = builder.build().toString();
        return url;
    }
}
