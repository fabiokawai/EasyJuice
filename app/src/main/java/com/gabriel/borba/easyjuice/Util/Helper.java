package com.gabriel.borba.easyjuice.Util;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabriel.borba.easyjuice.DAO.DrinksDAO;
import com.gabriel.borba.easyjuice.Models.Drink;
import com.gabriel.borba.easyjuice.activities.BasketActivity;

/**
 * Created by gabri on 01/07/2018.
 */
import com.gabriel.borba.easyjuice.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {

    private final EditText campoNome;
    private final EditText campoEstabelecimento;
    private final EditText campoPreco;
    private final TextView campoTamanho;
    private final TextView campoLatitude;
    private final TextView campoLongitude;
    private final List<View> arrayTamanhos;
    private Drink drink;


    public Helper(BasketActivity activity){
        campoNome = activity.findViewById(R.id.nome);
        campoEstabelecimento = activity.findViewById(R.id.estabelecimento);
        campoPreco = activity.findViewById(R.id.preco);
        campoTamanho = activity.findViewById(R.id.tamanhobebida);
        campoLatitude = activity.findViewById(R.id.latitude);
        campoLongitude = activity.findViewById(R.id.longitude);
        arrayTamanhos = Arrays.asList(activity.findViewById(R.id.latinha),activity.findViewById(R.id.lata),activity.findViewById(R.id.latao),activity.findViewById(R.id.longneck),activity.findViewById(R.id.garrafa),activity.findViewById(R.id.litrao),activity.findViewById(R.id.barril));
        drink = new Drink();
    }

    public Drink pegaDrink() {
        drink.setNome(campoNome.getText().toString());
        drink.setEstabelecimento(campoEstabelecimento.getText().toString());
        drink.setPreco(Double.parseDouble(campoPreco.getText().toString()));
        drink.setTamanho(Integer.parseInt(campoTamanho.getText().toString()));
        drink.setLatitude(Double.parseDouble(campoLatitude.getText().toString()));
        drink.setLongitude(Double.parseDouble(campoLongitude.getText().toString()));
        return drink;
    }

    public void preencheFormulario(Drink drink)
    {
        campoNome.setText(drink.getNome());
        campoEstabelecimento.setText(drink.getEstabelecimento());
        campoPreco.setText(String.valueOf(drink.getPreco()));
        campoLatitude.setText(String.valueOf(drink.getLatitude()));
        campoLongitude.setText(String.valueOf(drink.getLongitude()));
        preencheImagem(drink.getTamanho(),(ImageView) arrayTamanhos.get(drink.getTamanho() - 1));
        this.drink = drink;
    }

    private void preencheImagem(Integer tamanho,ImageView imgAux) {
        switch (tamanho){
            case 1:
                imgAux.setBackgroundResource(R.mipmap.ic_latinha_selecionada);
                break;
            case 2:
                imgAux.setBackgroundResource(R.mipmap.ic_lata_selecionada);
                break;
            case 3:
                imgAux.setBackgroundResource(R.mipmap.ic_latao_selecionada);
                break;
            case 4:
                imgAux.setBackgroundResource(R.mipmap.ic_longneck_selecionada);
                break;
            case 5:
                imgAux.setBackgroundResource(R.mipmap.ic_garrafa_selecionada);
                break;
            case 6:
                imgAux.setBackgroundResource(R.mipmap.ic_litrao_selecionada);
                break;
            case 7:
                imgAux.setBackgroundResource(R.mipmap.ic_barril_selecionada);
                break;

        }
    }


}

