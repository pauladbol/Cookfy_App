package com.livrodereceitas.cookfy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class  DetalheActivity extends AppCompatActivity {
    private DetalheHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        helper = new DetalheHelper(this);

        Intent intent = getIntent();
        Recipes receita = (Recipes) intent.getSerializableExtra("receita");

        helper.preencheDetalhe(receita);

//        String[] ingredientes = {"Ingrediente1", "Ingrediente2", "Ingrediente3"};
//        ListView lista_ingredientes = (ListView) findViewById(R.id.lista_ingredientes);
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, ingredientes);
//        lista_ingredientes.setAdapter(adapter);
    }
}