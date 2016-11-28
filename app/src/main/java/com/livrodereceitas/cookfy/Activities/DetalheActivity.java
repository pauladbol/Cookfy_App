package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Helpers.DetalheHelper;
import com.livrodereceitas.cookfy.R;
import com.livrodereceitas.cookfy.Classes.Recipes;


import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;


public class  DetalheActivity extends AppCompatActivity {
    public static final String REGISTER_URL = "https://cookfy.herokuapp.com/recipes/";
    public static final String PREFS_NAME = "MyPrefsFile";
    private DetalheHelper helper;
    Recipes receita;
    ArrayList<String> ingredientesArray = new ArrayList<String>();
    Boolean ehfavorito;
    byte[] imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        helper = new DetalheHelper(this);

        final CheckBox favorito = (CheckBox) findViewById(R.id.favorita);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        receita = (Recipes) intent.getParcelableExtra("receita");
        ingredientesArray = intent.getStringArrayListExtra("ingredientes");
        ehfavorito = intent.getBooleanExtra("favorito",false);

        TextView nomeReceita = (TextView) this.findViewById(R.id.nome_receita);
        TextView descricaoReceita = (TextView) this.findViewById(R.id.descricao);
        TextView ingredientes = (TextView) this.findViewById(R.id.descricao1);
        TextView dificuldade = (TextView) this.findViewById(R.id.dificuldadeTexto);
        TextView time = (TextView) this.findViewById(R.id.timeTexto);
        ImageView foto = (ImageView) this.findViewById(R.id.imagem);

        String ingredientesString = montaStringIngredientes(ingredientesArray);



        if (receita.getImagem2().length != 0) {
            Bitmap bitNew = BitmapFactory.decodeByteArray(receita.getImagem2(), 0, receita.getImagem2().length);

            foto.setScaleType(ImageView.ScaleType.CENTER_CROP);
            foto.setImageBitmap(bitNew);
        } else {
            foto.setImageResource(R.drawable.imagem);
        }

        nomeReceita.setText(receita.getName());
        descricaoReceita.setText(receita.getDescription());
        ingredientes.setText(ingredientesString);
        time.setText(receita.getExecutionTime());

        String dificuldadeReceita = converteDificuldade(receita.getDifficulty());


        dificuldade.setText(dificuldadeReceita);

        favorito.setChecked(ehfavorito);


        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favorito.isChecked()){
                    Favoritar();
                }
                else
                DesFavoritar();
            }
        });
        //helper.preencheDetalhe(receita);

//        String[] ingredientes = {"Ingrediente1", "Ingrediente2", "Ingrediente3"};
//        ListView lista_ingredientes = (ListView) findViewById(R.id.lista_ingredientes);
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, ingredientes);
//        lista_ingredientes.setAdapter(adapter);
    }

    private void Favoritar(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        String urlFavoritar = REGISTER_URL + receita.getId() +"/reacts?user=" + settings.getString("id","") +"&react=FAVORITY";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlFavoritar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(DetalheActivity.this, receita.getName() +" adiconada aos favoritos", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(DetalheActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void DesFavoritar(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        String urlFavoritar = REGISTER_URL + receita.getId() +"/reacts?user=" + settings.getString("id","") +"&react=FAVORITY";

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, urlFavoritar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(DetalheActivity.this, receita.getName() +" retirada dos favoritos", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(DetalheActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public String montaStringIngredientes(ArrayList<String> ingredientesArray){
        String ingredientesString = "";

        for(int i = 0; i < ingredientesArray.size(); i++) {
            ingredientesString += ingredientesArray.get(i) + "\n";
        }
        return ingredientesString;
    }

    public String converteDificuldade(String dificuldade){

        if (dificuldade.equals("HARD")) {
            return  "Difícil";
        } else if (dificuldade.equals("MEDIUM")) {
            return  "Média";
        } else {
            return  "Fácil";
        }

    }
}
