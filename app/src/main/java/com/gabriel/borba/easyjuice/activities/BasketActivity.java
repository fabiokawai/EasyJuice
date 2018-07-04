package com.gabriel.borba.easyjuice.activities;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gabriel.borba.easyjuice.DAO.DrinksDAO;
import com.gabriel.borba.easyjuice.Models.Drink;
import com.gabriel.borba.easyjuice.R;
import com.gabriel.borba.easyjuice.Util.Helper;

public class BasketActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    private Helper helper;

    private ImageButton latinha;
    private ImageButton lata;
    private ImageButton latao;
    private ImageButton longneck;
    private ImageButton garrafa;
    private ImageButton litrao;
    private ImageButton barril;

    private boolean latinhaAux = false;
    private boolean lataAux = false;
    private boolean lataoAux = false;
    private boolean longneckAux = false;
    private boolean garrafaAux = false;
    private boolean litraoAux = false;
    private boolean barrilAux = false;
    private TextView tamanhoBebida;

    private TextView latitude;
    private TextView longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper = new Helper(this);
        latinha = findViewById(R.id.latinha);
        lata = findViewById(R.id.lata);
        latao = findViewById(R.id.latao);
        longneck = findViewById(R.id.longneck);
        garrafa = findViewById(R.id.garrafa);
        litrao = findViewById(R.id.litrao);
        barril = findViewById(R.id.barril);
        tamanhoBebida = findViewById(R.id.tamanhobebida);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);


        Intent intent = getIntent();
        Drink drink = (Drink) intent.getSerializableExtra("drink");

        if(drink != null)
        {
            DrinksDAO dao = new DrinksDAO(this);
            Drink drinkdao = (Drink) dao.validaDrink(drink.getNome(),drink.getEstabelecimento());
            dao.close();
            helper.preencheFormulario(drinkdao);
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        setPermission();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basket, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.save_item:
                Drink drink = helper.pegaDrink();
                DrinksDAO dao = new DrinksDAO(this);
                if(drink.getId() != null)
                {
                    dao.altera(drink);
                }else {
                    dao.insere(drink);
                }
                dao.close();

                Toast.makeText(this, "Produto " + drink.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selecionaLatinha(View view) {selecionaitem(R.id.latinha);}
    public void selecionaLata(View view) {selecionaitem(R.id.lata);}
    public void selecionaLatao(View view) {selecionaitem(R.id.latao);}
    public void selecionaLongNeck(View view) {selecionaitem(R.id.longneck);}
    public void selecionaGarrafa(View view) {selecionaitem(R.id.garrafa);}
    public void selecionaLitrao(View view) {selecionaitem(R.id.litrao);}
    public void selecionaBarril(View view) {selecionaitem(R.id.barril);}

    private void selecionaitem(int item) {
        switch(item)
        {
            case R.id.latinha:
                latinhaAux = !latinhaAux;
                if(latinhaAux) {
                    tamanhoBebida.setText("1");
                    lataAux = lataoAux = longneckAux = garrafaAux = litraoAux = barrilAux = false;
                    lata.setBackgroundResource(R.mipmap.ic_lata);
                    latao.setBackgroundResource(R.mipmap.ic_latinha);
                    longneck.setBackgroundResource(R.mipmap.ic_longneck);
                    garrafa.setBackgroundResource(R.mipmap.ic_garrafa);
                    litrao.setBackgroundResource(R.mipmap.ic_litrao);
                    barril.setBackgroundResource(R.mipmap.ic_barril);
                    latinha.setBackgroundResource(R.mipmap.ic_latinha_selecionada);
                }else{
                    tamanhoBebida.setText("0");
                    latinha.setBackgroundResource(R.mipmap.ic_latinha);
                }
                break;
            case R.id.lata:
                lataAux = !lataAux;
                if(lataAux) {
                    tamanhoBebida.setText("2");
                    latinhaAux = lataoAux = longneckAux = garrafaAux = litraoAux = barrilAux = false;
                    latinha.setBackgroundResource(R.mipmap.ic_latinha);
                    latao.setBackgroundResource(R.mipmap.ic_latinha);
                    longneck.setBackgroundResource(R.mipmap.ic_longneck);
                    garrafa.setBackgroundResource(R.mipmap.ic_garrafa);
                    litrao.setBackgroundResource(R.mipmap.ic_litrao);
                    barril.setBackgroundResource(R.mipmap.ic_barril);
                    lata.setBackgroundResource(R.mipmap.ic_lata_selecionada);
                }else{
                    tamanhoBebida.setText("0");
                    lata.setBackgroundResource(R.mipmap.ic_lata);
                }
                break;
            case R.id.latao:
                lataoAux = !lataoAux;
                if(lataoAux) {
                    tamanhoBebida.setText("3");
                    latinhaAux = lataAux = longneckAux = garrafaAux = litraoAux = barrilAux = false;
                    latinha.setBackgroundResource(R.mipmap.ic_latinha);
                    lata.setBackgroundResource(R.mipmap.ic_lata);
                    longneck.setBackgroundResource(R.mipmap.ic_longneck);
                    garrafa.setBackgroundResource(R.mipmap.ic_garrafa);
                    litrao.setBackgroundResource(R.mipmap.ic_litrao);
                    barril.setBackgroundResource(R.mipmap.ic_barril);
                    latao.setBackgroundResource(R.mipmap.ic_latao_selecionada);
                }else{
                    tamanhoBebida.setText("0");
                    latao.setBackgroundResource(R.mipmap.ic_latao);
                }
                break;
            case R.id.longneck:
                longneckAux = !longneckAux;
                if(longneckAux) {
                    tamanhoBebida.setText("4");
                    latinhaAux = lataAux = lataoAux = garrafaAux = litraoAux = barrilAux = false;
                    latinha.setBackgroundResource(R.mipmap.ic_latinha);
                    lata.setBackgroundResource(R.mipmap.ic_lata);
                    latao.setBackgroundResource(R.mipmap.ic_latinha);
                    garrafa.setBackgroundResource(R.mipmap.ic_garrafa);
                    litrao.setBackgroundResource(R.mipmap.ic_litrao);
                    barril.setBackgroundResource(R.mipmap.ic_barril);
                    longneck.setBackgroundResource(R.mipmap.ic_longneck_selecionada);
                }else{
                    tamanhoBebida.setText("0");
                    longneck.setBackgroundResource(R.mipmap.ic_longneck);
                }
                break;
            case R.id.garrafa:
                garrafaAux = !garrafaAux;
                if(garrafaAux) {
                    tamanhoBebida.setText("5");
                    latinhaAux = lataAux = lataoAux = longneckAux = litraoAux = barrilAux = false;
                    latinha.setBackgroundResource(R.mipmap.ic_latinha);
                    lata.setBackgroundResource(R.mipmap.ic_lata);
                    latao.setBackgroundResource(R.mipmap.ic_latinha);
                    longneck.setBackgroundResource(R.mipmap.ic_longneck);
                    litrao.setBackgroundResource(R.mipmap.ic_litrao);
                    barril.setBackgroundResource(R.mipmap.ic_barril);
                    garrafa.setBackgroundResource(R.mipmap.ic_garrafa_selecionada);
                }else{
                    tamanhoBebida.setText("0");
                    garrafa.setBackgroundResource(R.mipmap.ic_garrafa);
                }
                break;
            case R.id.litrao:
                litraoAux = !litraoAux;
                if(litraoAux) {
                    tamanhoBebida.setText("6");
                    latinhaAux = lataAux = lataoAux = longneckAux = garrafaAux = barrilAux = false;
                    latinha.setBackgroundResource(R.mipmap.ic_latinha);
                    lata.setBackgroundResource(R.mipmap.ic_lata);
                    latao.setBackgroundResource(R.mipmap.ic_latinha);
                    longneck.setBackgroundResource(R.mipmap.ic_longneck);
                    garrafa.setBackgroundResource(R.mipmap.ic_garrafa);
                    barril.setBackgroundResource(R.mipmap.ic_barril);
                    litrao.setBackgroundResource(R.mipmap.ic_litrao_selecionada);
                }else{
                    tamanhoBebida.setText("0");
                    litrao.setBackgroundResource(R.mipmap.ic_litrao);
                }
                break;
            case R.id.barril:
                barrilAux = !barrilAux;
                if(barrilAux) {
                    tamanhoBebida.setText("7");
                    latinhaAux = lataAux = lataoAux = longneckAux = garrafaAux = litraoAux = false;
                    latinha.setBackgroundResource(R.mipmap.ic_latinha);
                    lata.setBackgroundResource(R.mipmap.ic_lata);
                    latao.setBackgroundResource(R.mipmap.ic_latinha);
                    longneck.setBackgroundResource(R.mipmap.ic_longneck);
                    garrafa.setBackgroundResource(R.mipmap.ic_garrafa);
                    litrao.setBackgroundResource(R.mipmap.ic_litrao);
                    barril.setBackgroundResource(R.mipmap.ic_barril_selecionada);
                }else{
                    tamanhoBebida.setText("0");
                    barril.setBackgroundResource(R.mipmap.ic_barril);
                }
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location)
    {
        String lon = String.valueOf(location.getLongitude());
        String lat = String.valueOf(location.getLatitude());

        latitude.setText(lat);
        longitude.setText(lon);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        setPermission();
    }

    private void setPermission()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            //Solicitando permissão
            ActivityCompat.requestPermissions(BasketActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            return;
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, BasketActivity.this);
        }
    }

}
