package com.ibtikar.app.easyclean.ui.fragments.pricing;

import android.net.Uri;

import com.google.gson.Gson;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.responses.CleanerCategoriesResponse;
import com.ibtikar.app.easyclean.ui.activities.base.BasePresenter;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class PricingPresenter <V extends PricingMvpView> extends BasePresenter<V> implements PricingMvpPresenter<V> {


    public PricingPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCategories(String cleanerId) {
        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(buildUrl(cleanerId))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String stringResponse = response.body().string();
                CleanerCategoriesResponse categoriesResponse = new Gson().fromJson(stringResponse, CleanerCategoriesResponse.class);

                if (categoriesResponse.isStatus())
                {
                    for (int i = 0 ; i < categoriesResponse.getDetails().getCategories().size(); i++)
                    {
                        getMvpView().addCategoryFragment(categoriesResponse.getDetails().getCategories().get(i).getId().toString());
                    }

                    for (int i = 0 ; i < categoriesResponse.getDetails().getCategories().size(); i++)
                    {
                        getMvpView().addCategoryTab(categoriesResponse.getDetails().getCategories().get(i).getName(),i);
                    }
                }
            }
        });
    }





    private String buildUrl(String cleanerId) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(StaticValues.URL_AUOTHORITY)
                .appendPath("mob")
                .appendPath("cleaner_cats")
                .appendPath(cleanerId);
        String url = builder.build().toString();
        return url;
    }
}
