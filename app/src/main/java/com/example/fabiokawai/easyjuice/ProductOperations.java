package com.example.fabiokawai.easyjuice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductOperations {

    private SimpleDBWrapper dbHelper;
    private String[] PRODUCT_TABLE_COLUMNS = { SimpleDBWrapper.PRODUCT_ID, SimpleDBWrapper.PRODUCT_NAME, SimpleDBWrapper.PRODUCT_PRICE, SimpleDBWrapper.PRODUCT_LOCATION, SimpleDBWrapper.PRODUCT_SIZE};
    private SQLiteDatabase database;

    public ProductOperations(Context context) {
        dbHelper = new SimpleDBWrapper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Product addProduct(String name, double price, String location, int size){
        ContentValues values = new ContentValues();
        values.put(SimpleDBWrapper.PRODUCT_NAME, name);
        values.put(SimpleDBWrapper.PRODUCT_PRICE, price);
        values.put(SimpleDBWrapper.PRODUCT_LOCATION, location);
        values.put(SimpleDBWrapper.PRODUCT_SIZE, size);
        long productId = database.insert(SimpleDBWrapper.PRODUCTS, null, values);

        Cursor cursor = database.query(SimpleDBWrapper.PRODUCTS,
                PRODUCT_TABLE_COLUMNS, SimpleDBWrapper.PRODUCT_ID + "="
                + productId, null, null, null, null);

        cursor.moveToFirst();

        Product product = parseProduct(cursor);
        cursor.close();

        return product;
    }

    public List getAllProducts(){
        List products = new ArrayList<Product>();
        Cursor cursor = database.query(SimpleDBWrapper.PRODUCTS, PRODUCT_TABLE_COLUMNS,
                null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Product product = parseProduct(cursor);
            products.add(product);
            cursor.moveToNext();
        }
        cursor.close();

        return products;
    }

    private Product parseProduct(Cursor cursor) {
        Product product = new Product();
        product.setId(cursor.getInt(0));
        product.setName(cursor.getString(1));
        product.setPrice(cursor.getDouble(2));
        product.setLocation(cursor.getString(3));
        product.setSize(cursor.getInt(4));
        return product;
    }


}
