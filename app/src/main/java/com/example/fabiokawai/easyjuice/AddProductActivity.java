package com.example.fabiokawai.easyjuice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddProductActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
    }

    public void saveClick(View view) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("teste", 1);

        setResult(RESULT_OK, resultIntent);

        finish();
    }
}
