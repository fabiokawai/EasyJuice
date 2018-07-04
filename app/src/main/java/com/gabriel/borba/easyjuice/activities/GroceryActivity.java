package com.gabriel.borba.easyjuice.activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gabriel.borba.easyjuice.DAO.DrinksDAO;
import com.gabriel.borba.easyjuice.Models.Drink;
import com.gabriel.borba.easyjuice.Models.DrinkAdapter;
import com.gabriel.borba.easyjuice.R;

import java.util.List;

public class GroceryActivity extends AppCompatActivity {

private android.support.v7.widget.Toolbar toolbar;
private android.support.v7.widget.Toolbar srcToolbar;
private Spinner spDistancia;
private Spinner spTamanhos;
private EditText filterNome;
private String arrayDistancias[] = {"2 Metros", "5 Metros", "10 Metros", "> 10 Metros"};
private String arrayTamanhos[] = {"Latinha", "Lata", "Latão", "Longneck","Garrafa","Litrão","Barril"};
    
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        toolbar = findViewById(R.id.toolbar);
        srcToolbar = findViewById(R.id.search_toolbar);
        list = findViewById(R.id.grocery_list);
        spDistancia = findViewById(R.id.spinnerDistancia);
        spTamanhos = findViewById(R.id.spinnerTamanhos);
        filterNome = findViewById(R.id.filterNome);
        setSupportActionBar(toolbar);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id)
            {
                Drink drink = (Drink) list.getItemAtPosition(position);
                Intent intentBasket = new Intent(GroceryActivity.this,BasketActivity.class);
                intentBasket.putExtra("drink",drink);
                startActivity(intentBasket);

            }
        });

        ArrayAdapter<String> adpDist = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayDistancias);
        spDistancia.setAdapter(adpDist);

        ArrayAdapter<String> adpTam = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayTamanhos);
        spTamanhos.setAdapter(adpTam);

        registerForContextMenu(list);
        carregaLista();
    }
    
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_tab_control, menu);
            return true;
        }

    private void carregaLista()
    {
        DrinksDAO dao = new DrinksDAO(this);
        List<Drink> alunos = dao.buscaDrink();
        dao.close();
        DrinkAdapter adapter = new DrinkAdapter(this, alunos);
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        carregaLista();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu:
                Intent acessaCarrinho  = new Intent(this, BasketActivity.class);
                startActivity(acessaCarrinho);
                break;
            case R.id.search_menu:
                showSearchMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Drink drink = (Drink) list.getItemAtPosition(info.position);

        /*final MenuItem itemMapa = menu.add("Visitar no Mapa");
        itemMapa.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intentMapa = new Intent(Intent.ACTION_VIEW);
                intentMapa.setData(Uri.parse("geo:0,0?q=" + aluno.getEndereco()));
                itemMapa.setIntent(intentMapa);
                return false;
            }
        });*/

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                DrinksDAO dao = new DrinksDAO(GroceryActivity.this);
                dao.deleta(drink);
                dao.close();
                Toast.makeText(GroceryActivity.this, "Bebida " + drink.getNome() + " Deletado",Toast.LENGTH_SHORT).show();
                carregaLista();
                return false;
            }
        });
    }

    private void showSearchMenu() {
        if(srcToolbar.getVisibility() == View.VISIBLE)
        {
            srcToolbar.setVisibility(View.GONE);
        }else
        {
            srcToolbar.setVisibility(View.VISIBLE);
        }
    }
}
