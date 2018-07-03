package com.example.fabiokawai.easyjuice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int REQUEST_ADD_PRODUCT = 1;
    private static int REQUEST_FIND_PRODUCT = 2;
    private static int REQUEST_SHOW_RESULT = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                //ADICIONA AO BANCO
            }
            else{
                Toast.makeText(this, "Ocorreu um erro ao tentar adicionar o produto.", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == REQUEST_FIND_PRODUCT) {
            if (resultCode == RESULT_OK) {
                Intent showResult = new Intent(this, SearchResultActivity.class);
                startActivityForResult(showResult, REQUEST_SHOW_RESULT);
            }
            else{
                Toast.makeText(this, "Ocorreu um erro ao tentar realizar a busca.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
