package com.ibtikar.app.easyclean.data;

import com.ibtikar.app.easyclean.data.dbhelper.SqliteHandler;

public class DataManager {

    SharedPrefsHelper mSharedPrefsHelper;
    private SqliteHandler mSqliteHandler;

    public DataManager(SqliteHandler mSqliteHandler, SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
        this.mSqliteHandler = mSqliteHandler;
    }

}
