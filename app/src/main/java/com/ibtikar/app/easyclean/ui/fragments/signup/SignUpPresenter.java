package com.ibtikar.app.easyclean.ui.fragments.signup;

import android.net.Uri;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.responses.CitiesSpinnerResponse;
import com.ibtikar.app.easyclean.data.responses.DestrictsSpinnerResponse;
import com.ibtikar.app.easyclean.ui.activities.base.BasePresenter;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp3.MultipartBody.FORM;

public class SignUpPresenter <V extends SignUpMvpView> extends BasePresenter<V> implements SignUpMvpPresenter<V> {
    
    public SignUpPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void signup(ArrayList<String> data) {
        if (checkData(data))
        {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new MultipartBody.Builder()
                    .setType(FORM)
                    .addFormDataPart("name", data.get(0))
                    .addFormDataPart("email", data.get(3))
                    .addFormDataPart("password", data.get(1))
                    .addFormDataPart("area", data.get(4))
                    .build();

            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(buildUrl("register"))
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String stringResponse = response.body().string();

                    try {
                        JSONObject jsnMainobject = new JSONObject(stringResponse);
                        if (jsnMainobject.getBoolean("status"))
                        {
                            JSONObject jsnUserObject = jsnMainobject.getJSONObject("user");
                            String id = jsnUserObject.getString("id");
                            String name = jsnUserObject.getString("name");
                            String email = jsnUserObject.getString("email");
                            String socialtoken = jsnUserObject.getString("socialtoken");
                            getMvpView().showToast("تم التسجيل",StaticValues.FLAG_TOAST_String);
                        }

                        else {
                            if (jsnMainobject.getString("code").equals("0"))
                            {
                                getMvpView().showToast("برجاء تفعيل الحساب اولا",StaticValues.FLAG_TOAST_String);
                                getMvpView().showReActivateSnackbar("برجاء تفعيل الحساب اولا من خلال بريدك الإلكتروني");
                            }
                            else if (jsnMainobject.getString("code").equals("1"))
                            {
                                getMvpView().showSnackbarLogin();
                            }
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });




        }
    }

    @Override
    public void signupSocial(String socialType, String accessToken) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder()
                .setType(FORM)
                .addFormDataPart("socialtoken", accessToken)
                .addFormDataPart("type", socialType)
                .build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(buildUrl("sociallogin"))
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                getDataManager().setLogin(true);
                getMvpView().finishRegisteration();
            }
        });
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
                    getMvpView().setupCitiesSpinner(citiesResponse.getList());
                }
            }
        });
    }

    @Override
    public void loadDestricts(String cityId) {
        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(buildUrlDestricts(cityId))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String stringResponse = response.body().string();
                DestrictsSpinnerResponse citiesResponse = new Gson().fromJson(stringResponse, DestrictsSpinnerResponse.class);

                if (citiesResponse.isStatus())
                {
                    getMvpView().setupdestrictsSpinner(citiesResponse.getList());
                }
            }
        });
    }

    @Override
    public void resendActivation(String email) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder()
                .setType(FORM)
                .addFormDataPart("email", email)
                .build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(buildUrl("resendactivation"))
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String stringResponse = response.body().string();

                try {
                    JSONObject jsnMainObject = new JSONObject(stringResponse);
                    if (jsnMainObject.getBoolean("status"))
                    {
                        getMvpView().showActivationSentSnackBar();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private boolean checkData (ArrayList<String> data)
    {


        if (!data.get(1).equals(data.get(2)))
        {
            getMvpView().showToast(String.valueOf(R.string.password_not_match), StaticValues.FLAG_TOAST_INT);
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(data.get(3)).matches())
        {
            getMvpView().showToast(String.valueOf(R.string.email_not_valid),StaticValues.FLAG_TOAST_INT);
            return false;
        }

        if (data.get(4) == null || data.get(4).isEmpty()){
            getMvpView().showToast(String.valueOf(R.string.choose_destrict),StaticValues.FLAG_TOAST_INT);
            return false;
        }

        for (int i = 0 ; i< data.size();i++)
        {
            if (data.get(i) == null || data.get(i).isEmpty()) {
                getMvpView().showToast(String.valueOf(R.string.fields_empty), StaticValues.FLAG_TOAST_INT);
                return false;
            }
        }

        return true;
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

    private String buildUrlDestricts(String cityId) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(StaticValues.URL_AUOTHORITY)
                .appendPath("mob")
                .appendPath("districts")
                .appendPath(cityId);
        String url = builder.build().toString();
        return url;
    }

}
