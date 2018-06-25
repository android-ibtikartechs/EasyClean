package com.ibtikar.app.easyclean;

import android.app.Application;

import com.ibtikar.app.easyclean.data.DataManager;
import com.ibtikar.app.easyclean.data.SharedPrefsHelper;
import com.ibtikar.app.easyclean.data.dbhelper.SqliteHandler;

public class MvpApp extends Application {

    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        SqliteHandler sqliteHandler = new SqliteHandler(getApplicationContext());
        dataManager = new DataManager(sqliteHandler, sharedPrefsHelper);

    }


    public DataManager getDataManager() {
        return dataManager;
    }

}
