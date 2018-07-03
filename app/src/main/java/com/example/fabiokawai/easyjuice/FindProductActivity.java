package com.example.fabiokawai.easyjuice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FindProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_product);
    }

    public void findClick(View view) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("teste", 1);

        setResult(RESULT_OK, resultIntent);

        finish();
    }
}
