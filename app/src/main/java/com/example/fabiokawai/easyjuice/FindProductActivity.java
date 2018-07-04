package com.example.fabiokawai.easyjuice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class FindProductActivity extends AppCompatActivity {

    private EditText txtProductName;
    private SeekBar distanceSeekBar;
    private TextView distanceValue;

    private int distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_product);
        txtProductName = (EditText) findViewById(R.id.txtProductName);
        distanceSeekBar = (SeekBar) findViewById(R.id.skDistance);
        distanceValue = (TextView) findViewById(R.id.txtDistance);

        distanceSeekBar.setOnSeekBarChangeListener(distanceSeekBarListener);
    }

    private SeekBar.OnSeekBarChangeListener distanceSeekBarListener  = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (progress){
                case 0:
                    distance = 2;
                    distanceValue.setText("2");
                    break;
                case 1:
                    distance = 5;
                    distanceValue.setText("5");
                    break;
                case 2:
                    distance = 10;
                    distanceValue.setText("10");
                    break;
                case 3:
                    distance = 999;
                    distanceValue.setText("MAX");
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void findClick(View view) {
        try{
            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", txtProductName.getText().toString());
            resultIntent.putExtra("distance", distanceSeekBar.getProgress());

            setResult(RESULT_OK, resultIntent);
        }
        catch (Exception e){
            setResult(RESULT_CANCELED);
        }

        finish();
    }
}
