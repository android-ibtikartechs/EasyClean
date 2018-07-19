package com.ibtikar.app.easyclean.ui.fragments.login;

import android.net.Uri;

import com.ibtikar.app.easyclean.R;
import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.ui.activities.base.BasePresenter;
import com.ibtikar.app.easyclean.utilities.StaticValues;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp3.MultipartBody.FORM;

public class LoginPresenter <V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void login(String email, String password) {
        if (checkData(email,password))
        {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new MultipartBody.Builder()
                    .setType(FORM)
                    .addFormDataPart("email", email)
                    .addFormDataPart("password", password)
                    .build();

            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(buildUrl("login"))
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
                            getDataManager().setLogin(true);
                            getMvpView().showToast("تم تسجيل الدخول بنجاح", StaticValues.FLAG_TOAST_String);
                            getMvpView().finishRegestration();
                        }

                        else
                        {
                            if (jsnMainobject.getInt("code") == 2 )
                            {
                                getMvpView().showToast("خطأ في كلمة المرور", StaticValues.FLAG_TOAST_String);
                            }
                            else if (jsnMainobject.getInt("code") == 3)
                            {
                                getMvpView().showToast("البيانات المدخلة غير صحيحة", StaticValues.FLAG_TOAST_String);
                            }

                            else if (jsnMainobject.getInt("code") == 0)
                            {
                                getMvpView().showReActivateSnackbar("برجاء تفعيل الحساب اولا من خلال بريدك الإلكتروني");
                            }

                            else
                            {
                                getMvpView().showToast("البيانات المدخلة غير صحيحة", StaticValues.FLAG_TOAST_String);
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

    private String buildUrl(String lastAppendPatth) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(StaticValues.URL_AUOTHORITY)
                .appendPath("mob")
                .appendPath(lastAppendPatth);
        String url = builder.build().toString();
        return url;
    }

    boolean checkData(String email, String password)
    {
        if (email.isEmpty() || password.isEmpty()) {
            getMvpView().showToast(String.valueOf(R.string.fields_empty),StaticValues.FLAG_TOAST_INT);
            return false;
        }
        return true;
    }
}
