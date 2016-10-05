package com.livrodereceitas.cookfy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
//AppCompatActivity
public class ListaReceitasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receitas);
        final GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new Adaptador(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipes receita = (Recipes) gridView.getItemAtPosition(position);
                Toast.makeText(ListaReceitasActivity.this, "receita" + receita.getName(), Toast.LENGTH_SHORT).show();
                Intent intentVaiPraDetalheReceita = new Intent(ListaReceitasActivity.this, DetalheActivity.class);
                intentVaiPraDetalheReceita.putExtra("receita", receita);
                startActivity(intentVaiPraDetalheReceita);
            }
        });
    }
}
