package com.ibtikar.app.easyclean.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsHelper {
    public static final String MY_PREFS = "MY_PREFS";
    SharedPreferences mSharedPreferences;
    private static final String EMAIL = "Email";
    private static final String ISLOGIN = "IsLogin";


    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
    }

    public void setLogin (boolean isLogin)
    {
        mSharedPreferences.edit().putBoolean(ISLOGIN, isLogin).apply();
    }

    public boolean isLogIn()
    {
        return mSharedPreferences.getBoolean(ISLOGIN,false);
    }
}
