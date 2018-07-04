package com.gabriel.borba.easyjuice.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gabriel.borba.easyjuice.Models.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinksDAO extends SQLiteOpenHelper {

    public DrinksDAO(Context context) {
        super(context, "Market", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Bebidas (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, estabelecimento TEXT, preco REAL, tamanho INTEGER, latitude REAL, longitude REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void insere(Drink drink) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDeDrink(drink);
        db.insert("Bebidas", null,dados);
    }

    public Drink validaDrink(String nome,String estabelecimento) {
        String sql = "SELECT * FROM Bebidas WHERE nome = ? AND estabelecimento = ?;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,new String[]{nome,estabelecimento});
        Drink drink = new Drink();

        while (c.moveToNext()){
            drink.setId(c.getLong(c.getColumnIndex("id")));
            drink.setNome(c.getString(c.getColumnIndex("nome")));
            drink.setEstabelecimento(c.getString(c.getColumnIndex("estabelecimento")));
            drink.setPreco(c.getDouble(c.getColumnIndex("preco")));
            drink.setTamanho(c.getInt(c.getColumnIndex("tamanho")));
            drink.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
            drink.setLongitude(c.getDouble(c.getColumnIndex("longitude")));
        }
        c.close();
        return drink;
    }

    private ContentValues pegaDadosDeDrink(Drink drink) {
        ContentValues dados = new ContentValues();
        dados.put("nome", drink.getNome());
        dados.put("estabelecimento", drink.getEstabelecimento());
        dados.put("preco", drink.getPreco());
        dados.put("tamanho", drink.getTamanho());
        dados.put("latitude", drink.getLatitude());
        dados.put("longitude", drink.getLongitude());
        return dados;
    }

    public List<Drink> buscaDrink() {
        String sql = "SELECT * FROM Bebidas;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Drink> drinks = new ArrayList<Drink>();

        while (c.moveToNext()){
            Drink drink = new Drink();
            drink.setId(c.getLong(c.getColumnIndex("id")));
            drink.setNome(c.getString(c.getColumnIndex("nome")));
            drink.setEstabelecimento(c.getString(c.getColumnIndex("estabelecimento")));
            drink.setPreco(c.getDouble(c.getColumnIndex("preco")));
            drink.setTamanho(c.getInt(c.getColumnIndex("tamanho")));
            drink.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
            drink.setLongitude(c.getDouble(c.getColumnIndex("longitude")));
            drinks.add(drink);
        }
        c.close();
        return drinks;
    }

    public void deleta(Drink drink) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {drink.getId().toString()};
        db.delete("Bebidas","id = ?",params);
    }

    public void altera(Drink drink) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDeDrink(drink);
        String[] params = {drink.getId().toString()};
        db.update("Bebidas",dados,"id = ?",params);
    }
}
