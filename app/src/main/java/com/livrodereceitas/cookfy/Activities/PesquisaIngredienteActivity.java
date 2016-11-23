package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Adapters.GridIngredienteAdapter;
import com.livrodereceitas.cookfy.Classes.Ingrediente;
import com.livrodereceitas.cookfy.Classes.Recipes;
import com.livrodereceitas.cookfy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PesquisaIngredienteActivity extends AppCompatActivity {
    private static final String REGISTER_URL = "https://cookfy.herokuapp.com/recipes/ingredients?";
    private ArrayList<Recipes> receitasList = new ArrayList<Recipes>();
    private List<Ingrediente> listaIngredientesPesquisa = new ArrayList<Ingrediente>();
    Ingrediente ingrediente;

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
                ingrediente = new Ingrediente();
                final EditText ingrediente = (EditText) findViewById(R.id.ingredienteNome);
                if (!validarIngrediente(ingrediente.getText().toString())) {
                    ingrediente.setError("O campo ingrediente não pode ser vazio");
                    ingrediente.requestFocus();}
                else {

                    PesquisaIngredienteActivity.this.ingrediente.setNome(ingrediente.getText().toString());
//                    PesquisaIngredienteActivity.this.ingrediente.getNome().replace(" ", "%20");
                    listaIngredientesPesquisa.add(PesquisaIngredienteActivity.this.ingrediente);
                    ingrediente.setText("");
                    baseAdapter.notifyDataSetChanged();
                }
            }
        });


        pesquisar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               PesquisaIngrediente();


            }
        });

        gridView.setAdapter(baseAdapter);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ingrediente = (Ingrediente) gridView.getItemAtPosition(position);
                listaIngredientesPesquisa.remove(position);
                baseAdapter.notifyDataSetChanged();
                Toast.makeText(PesquisaIngredienteActivity.this, "ingrediente " + ingrediente.getNome() + " apagado", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
    public boolean validarIngrediente(String ingrediente) {
        String nomePattern = "^[aA-zZ]{2,}+(([ aA-zZ]+)+)?$";
        Pattern pattern = Pattern.compile(nomePattern);
        Matcher matcher = pattern.matcher(ingrediente);
        return matcher.matches();
    }
    public void PesquisaIngrediente(){
        String urlListaIngredientes = REGISTER_URL;
        if(listaIngredientesPesquisa != null){
        boolean primeira = true;
            for(Ingrediente teste: listaIngredientesPesquisa){
                if (primeira) {
                    urlListaIngredientes+= "ingredient=" + teste.getNome().replace(" ", "%20");
                    primeira = false;
                } else {
                    urlListaIngredientes += "&ingredient=" + teste.getNome().replace(" ", "%20");
                }
            }
            JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                    urlListaIngredientes, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        Log.i("fav","aqui");

                        for (int i = 0; i < response.length(); i++) {
                            Log.i("fav", "FAV "+response.toString());

                            JSONObject receitaJSON = response.getJSONObject(i);

                            Recipes receita = new Recipes();
                            receita.setId(receitaJSON.getString("id"));
                            receita.setName(receitaJSON.getString("name"));
                            receita.setDescription(receitaJSON.getString("description"));
                            receita.setExecutionTime(receitaJSON.getString("prepTime"));
                            receita.setDifficulty(receitaJSON.getString("difficulty"));
                            receita.setDrawableId(R.drawable.imagem);

                            receitasList.add(receita);
                        }

                        Toast.makeText(PesquisaIngredienteActivity.this, "!", Toast.LENGTH_LONG).show();
                        Intent intentFav = new Intent(PesquisaIngredienteActivity.this, ListaReceitasActivity.class);
                        intentFav.putParcelableArrayListExtra("receitasList", receitasList);
                        startActivity(intentFav);
                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(PesquisaIngredienteActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){


            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjReq);
        }else {
            Toast.makeText(PesquisaIngredienteActivity.this,"Você precisa adicionar um ingrediente para fazer a pesquisa",Toast.LENGTH_LONG).show();
        }

    }
}
