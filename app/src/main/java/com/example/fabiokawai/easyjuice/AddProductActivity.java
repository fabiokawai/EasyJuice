package com.example.fabiokawai.easyjuice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddProductActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtLocation;
    private EditText txtPrice;
    private Spinner sizeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        txtName = (EditText) findViewById(R.id.txtProductName);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        txtLocation = (EditText) findViewById(R.id.txtPlaceName);
        String[] productSize = new String[] {
                "latinha", "lata", "latão", "long neck", "garrafa", "litrão", "barril"
        };
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, productSize);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);
    }

    public void saveClick(View view) {
        Intent resultIntent = new Intent();
        try{
            if (areAllFieldsFilled()){
                resultIntent.putExtra("name", txtName.getText().toString());
                resultIntent.putExtra("location", txtLocation.getText().toString());
                resultIntent.putExtra("price", Double.parseDouble(txtPrice.getText().toString()));
                resultIntent.putExtra("size", sizeSpinner.getSelectedItemPosition());
                setResult(RESULT_OK, resultIntent);
            }
            else{
                setResult(RESULT_CANCELED);
            }
        }
        catch (Exception e){
            setResult(RESULT_CANCELED);
        }

        finish();
    }

    private boolean areAllFieldsFilled(){

        if (txtName.getText() == null || txtName.getText().toString().trim() == "")
            return false;

        if (txtLocation.getText() == null || txtLocation.getText().toString().trim() == "")
            return false;

        if (txtPrice.getText() == null || txtPrice.getText().toString().trim() == "")
            return false;

        return true;
    }


}
