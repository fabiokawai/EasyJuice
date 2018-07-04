package com.example.fabiokawai.easyjuice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int REQUEST_ADD_PRODUCT = 1;
    private static int REQUEST_FIND_PRODUCT = 2;
    private static int REQUEST_SHOW_RESULT = 3;

    private ProductOperations productOperations;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productOperations = new ProductOperations(this);
        productOperations.open();

        List values = productOperations.getAllProducts();
        list = (ListView) findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        productOperations.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        productOperations.close();
        super.onPause();
    }

    public void addClick(View view) {
        Intent i = new Intent(this, AddProductActivity.class);
        startActivityForResult(i, REQUEST_ADD_PRODUCT);
    }

    public void findClick(View view) {
        Intent i = new Intent(this, FindProductActivity.class);
        startActivityForResult(i, REQUEST_FIND_PRODUCT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_ADD_PRODUCT) {
            if (resultCode == RESULT_OK) {
                try{
                    String name = data.getStringExtra("name");
                    String location = data.getStringExtra("location");
                    Double price = (Double)data.getDoubleExtra("price", 0.00);
                    int size = data.getIntExtra("size", 0);
                    ArrayAdapter adapter = (ArrayAdapter) list.getAdapter();
                    Product product = productOperations.addProduct(name, price, location, size);
                    adapter.add(product);
                }
                catch (Exception e){
                    Toast.makeText(this, "Ocorreu um erro ao salvar produto no banco.", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                Toast.makeText(this, "Ocorreu um erro ao tentar adicionar o produto.", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == REQUEST_FIND_PRODUCT) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                int distance = data.getIntExtra("distance", 3);

                Intent showResult = new Intent(this, SearchResultActivity.class);
                showResult.putExtra("name", name);
                showResult.putExtra("distance", distance);
                startActivityForResult(showResult, REQUEST_SHOW_RESULT);
            }
            else{
                Toast.makeText(this, "Ocorreu um erro ao tentar realizar a busca.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
