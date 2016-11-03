package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.livrodereceitas.cookfy.Helpers.DetalheHelper;
import com.livrodereceitas.cookfy.R;
import com.livrodereceitas.cookfy.Classes.Recipes;

import java.util.ArrayList;

public class  DetalheActivity extends AppCompatActivity {
    public static final String REGISTER_URL = "https://cookfy.herokuapp.com/recipes/";
    private DetalheHelper helper;
    Recipes receita;
    ArrayList<String> ingredientesArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        helper = new DetalheHelper(this);

        final CheckBox favorito = (CheckBox) findViewById(R.id.favorita);

        Intent intent = getIntent();

        receita = (Recipes) intent.getSerializableExtra("receita");
        ingredientesArray = intent.getStringArrayListExtra("ingredientes");

        TextView nomeReceita = (TextView) this.findViewById(R.id.nome_receita);
        TextView descricaoReceita = (TextView) this.findViewById(R.id.descricao);
        TextView ingredientes = (TextView) this.findViewById(R.id.descricao1);

        String ingredientesString = "" /*ingredientesArray.toString()*/ ;

        for(int i = 0; i < ingredientesArray.size(); i++) {
            ingredientesString += ingredientesArray.get(i) + "\n";
        }

        Log.i("script","LISTA "+ingredientesArray.get(1));


        nomeReceita.setText(receita.getName());
        descricaoReceita.setText(receita.getDescription());
        ingredientes.setText(ingredientesString);

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

    private void Favoritar(){
       // String urlFavoritar = REGISTER_URL + receita.getId() +"/reacts?user" +
    }
}
