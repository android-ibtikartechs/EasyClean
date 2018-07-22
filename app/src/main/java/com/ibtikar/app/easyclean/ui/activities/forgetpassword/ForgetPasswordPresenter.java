package com.ibtikar.app.easyclean.ui.activities.forgetpassword;

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

public class ForgetPasswordPresenter <V extends ForgetPasswordMvpView> extends BasePresenter<V> implements ForgetPasswordMvpPresenter<V> {
    public ForgetPasswordPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void sendPassword(String email) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder()
                .setType(FORM)
                .addFormDataPart("email", email)
                .build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(buildUrl("forgetpassword_ver2"))
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
                    if (jsnMainObject.getString("status").equals("OK"))
                    {
                        getMvpView().showToast(String.valueOf(R.string.password_sent), StaticValues.FLAG_TOAST_INT);
                        getMvpView().finishSend();
                    }
                    else
                        getMvpView().showToast(String.valueOf(R.string.not_found), StaticValues.FLAG_TOAST_INT);
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
                .appendPath(lastAppendPatth);
        String url = builder.build().toString();
        return url;
    }


    boolean checkData(String email)
    {
        if (email.isEmpty()) {
            getMvpView().showToast(String.valueOf(R.string.fields_empty), StaticValues.FLAG_TOAST_INT);
            return false;
        }

        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            getMvpView().showToast(String.valueOf(R.string.email_not_valid),StaticValues.FLAG_TOAST_INT);
            return false;
        }

        return true;
    }
}
