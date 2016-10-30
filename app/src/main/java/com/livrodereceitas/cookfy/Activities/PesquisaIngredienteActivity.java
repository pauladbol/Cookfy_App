package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.livrodereceitas.cookfy.Adapters.GridIngredienteAdapter;
import com.livrodereceitas.cookfy.Classes.IngredientePesquisa;
import com.livrodereceitas.cookfy.R;

import java.util.ArrayList;
import java.util.List;

public class PesquisaIngredienteActivity extends AppCompatActivity {
    private List<IngredientePesquisa> listaIngredientesPesquisa = new ArrayList<IngredientePesquisa>();
    IngredientePesquisa ingredientePesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_ingrediente);

        Button adicionar = (Button) findViewById(R.id.ingredienteAdicionar);
        Button pesquisar = (Button) findViewById(R.id.ingredientePesquisar);
        final GridView gridView = (GridView) findViewById(R.id.gridIngredientes);
        final BaseAdapter baseAdapter = new GridIngredienteAdapter(this, listaIngredientesPesquisa);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientePesquisa = new IngredientePesquisa();
                EditText ingrediente = (EditText) findViewById(R.id.ingredienteNome);
                ingredientePesquisa.setNome(ingrediente.getText().toString());
                listaIngredientesPesquisa.add(ingredientePesquisa);
                ingrediente.setText("");
                baseAdapter.notifyDataSetChanged();

            }
        });


        pesquisar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        gridView.setAdapter(baseAdapter);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ingredientePesquisa = (IngredientePesquisa) gridView.getItemAtPosition(position);
                listaIngredientesPesquisa.remove(position);
                baseAdapter.notifyDataSetChanged();
                Toast.makeText(PesquisaIngredienteActivity.this, "ingrediente " + ingredientePesquisa.getNome() + " apagado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
}
