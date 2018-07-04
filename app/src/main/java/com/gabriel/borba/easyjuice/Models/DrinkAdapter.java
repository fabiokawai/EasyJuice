package com.gabriel.borba.easyjuice.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gabriel.borba.easyjuice.R;
import com.gabriel.borba.easyjuice.Util.Helper;
import com.gabriel.borba.easyjuice.activities.BasketActivity;

import java.util.List;

/**
 * Created by gabri on 01/07/2018.
 */

public class DrinkAdapter extends BaseAdapter {

    private final List<Drink> drinks;
    private final Context context;

    public DrinkAdapter(Context context, List<Drink> drinks) {
        this.drinks = drinks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return drinks.size();
    }

    @Override
    public Object getItem(int position) {
        return drinks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return drinks.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Drink drink = drinks.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_layout, parent, false);
        }

        TextView campoNome = view.findViewById(R.id.item_nome);
        campoNome.setText(drink.getNome());

        TextView campoEstabelcimento = view.findViewById(R.id.item_estabelecimento);
        campoEstabelcimento.setText(drink.getEstabelecimento());

        TextView campoPreco = view.findViewById(R.id.item_preco);
        campoPreco.setText(String.valueOf(drink.getPreco()));

        TextView campoLatitude = view.findViewById(R.id.item_latitude);
        campoLatitude.setText(String.valueOf(drink.getLatitude()));

        TextView campoLongitude = view.findViewById(R.id.item_longitude);
        campoLongitude.setText(String.valueOf(drink.getLongitude()));

        ImageView campoItem = view.findViewById(R.id.item_foto);
        preencheImagem(drink.getTamanho(),campoItem);
        return view;
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
