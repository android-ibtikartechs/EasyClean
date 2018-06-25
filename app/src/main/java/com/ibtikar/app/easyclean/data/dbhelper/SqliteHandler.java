package com.ibtikar.app.easyclean.data.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHandler extends SQLiteOpenHelper {

    Context context;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "list_of";

    public SqliteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
