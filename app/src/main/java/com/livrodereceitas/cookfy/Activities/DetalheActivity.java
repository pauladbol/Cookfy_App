package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.livrodereceitas.cookfy.Helpers.DetalheHelper;
import com.livrodereceitas.cookfy.R;
import com.livrodereceitas.cookfy.Classes.Recipes;

public class  DetalheActivity extends AppCompatActivity {
    private DetalheHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        helper = new DetalheHelper(this);

        final CheckBox favorito = (CheckBox) findViewById(R.id.favorita);
        Intent intent = getIntent();
        Recipes receita = (Recipes) intent.getSerializableExtra("receita");


        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favorito.isChecked()){
                    Toast.makeText(DetalheActivity.this, "agora é favorito " + favorito.isChecked() , Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(DetalheActivity.this, "não é mais favorito " + favorito.isChecked(), Toast.LENGTH_SHORT).show();
            }
        });
        //helper.preencheDetalhe(receita);

//        String[] ingredientes = {"Ingrediente1", "Ingrediente2", "Ingrediente3"};
//        ListView lista_ingredientes = (ListView) findViewById(R.id.lista_ingredientes);
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, ingredientes);
//        lista_ingredientes.setAdapter(adapter);
    }
}
