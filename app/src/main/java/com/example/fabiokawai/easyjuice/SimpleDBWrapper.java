package com.example.fabiokawai.easyjuice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SimpleDBWrapper extends SQLiteOpenHelper{

    public static final String PRODUCTS = "Products";
    public static final String PRODUCT_ID = "_id";
    public static final String PRODUCT_NAME = "_name";
    public static final String PRODUCT_PRICE = "_price";
    public static final String PRODUCT_LOCATION = "_location";

    private static final String DATABASE_NAME = "Products.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + PRODUCTS + "(" + PRODUCT_ID + " integer primary key autoincrement, "
            + PRODUCT_NAME + " text not null, " + PRODUCT_PRICE + " real not null, " + PRODUCT_LOCATION + " text not null);";



    public SimpleDBWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCTS);
        onCreate(db);
    }
}
