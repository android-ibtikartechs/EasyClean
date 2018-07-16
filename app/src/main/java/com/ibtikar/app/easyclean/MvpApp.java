package com.ibtikar.app.easyclean;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.SharedPrefsHelper;
import com.ibtikar.app.easyclean.data.dbhelper.SqliteHandler;

import java.util.Locale;

public class MvpApp extends Application {

    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        String lang_code = "ar"; //load it from SharedPref
        changeLang(getApplicationContext(), lang_code);

        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        SqliteHandler sqliteHandler = new SqliteHandler(getApplicationContext());
        dataManager = new DataManager(sqliteHandler, sharedPrefsHelper);



    }


    public DataManager getDataManager() {
        return dataManager;
    }


    public ContextWrapper changeLang(Context context, String lang_code) {
        Locale sysLocale;

        Resources rs = context.getResources();
        Configuration config = rs.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = config.getLocales().get(0);
        } else {
            sysLocale = config.locale;
        }
        if (!lang_code.equals("") && !sysLocale.getLanguage().equals(lang_code)) {
            String country = "ARE";
            Locale locale = new Locale(lang_code,country);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLayoutDirection(locale);
                context = context.createConfigurationContext(config);
            } else {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }

        }

        return new ContextWrapper(context);
    }

}
