package com.ibtikar.app.easyclean.ui.fragments.home;

import android.net.Uri;

import com.google.gson.Gson;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.responses.CitiesSpinnerResponse;
import com.ibtikar.app.easyclean.data.responses.HomeResponse;
import com.ibtikar.app.easyclean.ui.activities.base.BasePresenter;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp3.MultipartBody.FORM;

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
                .url(buildUrl("getallcleaners"))
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

    @Override
    public void loadCities() {
        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(buildUrl("cities"))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String stringResponse = response.body().string();
                CitiesSpinnerResponse citiesResponse = new Gson().fromJson(stringResponse, CitiesSpinnerResponse.class);

                if (citiesResponse.isStatus())
                {
                    getMvpView().setupSpinner(citiesResponse.getList());
                }
            }
        });
    }

    @Override
    public void search(String cityId) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder()
                .setType(FORM)
                .addFormDataPart("city", cityId)
                .build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(buildUrl("search"))
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String stringResponse = response.body().string();
                HomeResponse homeResponse = new Gson().fromJson(stringResponse, HomeResponse.class);

                if (homeResponse.isStatus())
                {
                    getMvpView().refreshListCleaners(homeResponse.getList());
                }
            }
        });


    }


    private String buildUrl(String lastAppendPatth) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(StaticValues.URL_AUOTHORITY)
                .appendPath("mob")
                .appendPath(lastAppendPatth);
        String url = builder.build().toString();
        return url;
    }
}
