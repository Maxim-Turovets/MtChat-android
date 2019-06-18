package com.example.mtchat_android.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private  static  final  int DATABASE_VERSION = 1;
    private   static final String  DATABASE_NAME = "settingDB";
    private static final String  TABLE_SETTING = "settings";

    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_STATUS = "isActive";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "+TABLE_SETTING + "("+KEY_ID
     + "integer primary key, "+KEY_NAME+" text,"+KEY_STATUS+" integer"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
