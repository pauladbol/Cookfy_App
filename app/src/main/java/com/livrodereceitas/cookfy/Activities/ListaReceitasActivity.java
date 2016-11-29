package com.livrodereceitas.cookfy.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.livrodereceitas.cookfy.Adapters.GridViewAdapter;
import com.livrodereceitas.cookfy.R;
import com.livrodereceitas.cookfy.Classes.Recipes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.livrodereceitas.cookfy.Classes.Recipes;

//AppCompatActivity
public class ListaReceitasActivity extends AppCompatActivity {

    private static final String REGISTER_URL = "https://cookfy.herokuapp.com/recipes/";
    public static final String PREFS_NAME = "MyPrefsFile";

    ArrayList<Recipes> listaReceitas = new ArrayList<Recipes>();
    private Recipes receitaTeste;

    Recipes receita = new Recipes();

    Boolean favorito = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receitas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final GridView gridView = (GridView) findViewById(R.id.gridview);

        listaReceitas = preencheReceitas();

        BaseAdapter baseAdapterReceita = new GridViewAdapter(this, listaReceitas);

        gridView.setAdapter(baseAdapterReceita);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                receita = (Recipes) gridView.getItemAtPosition(position);

                pegaReceita(receita);

            }
        });

    }

    private void pegaReceita(final Recipes receitaDetalhe){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        final String urlReceita = REGISTER_URL + receitaDetalhe.getId() + "?user=" + settings.getString("id","");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlReceita, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    receitaDetalhe.setName(response.getString("name"));
                    receitaDetalhe.setDescription(response.getString("description"));
                    receitaDetalhe.setId(response.getString("id"));
                    receitaDetalhe.setDifficulty(response.getString("difficulty"));
                    receitaDetalhe.setExecutionTime(response.getString("prepTime"));
                    String imgBytes = response.getString("picture");
                    if (imgBytes != "null" && imgBytes != ""){
                        byte[] imgRecebida = Base64.decode(imgBytes, Base64.DEFAULT);
                        receitaDetalhe.setImagem2(imgRecebida);
                    }


                    favorito = response.getBoolean("favority");

                    JSONArray ingredientes = response.getJSONArray("recipeIngredients");

                    ArrayList<String> ingredientesList = montalistaIngredientes(ingredientes);

                    Intent intentDetalhe = new Intent(ListaReceitasActivity.this, DetalheActivity.class);
                    intentDetalhe.putExtra("receita", receitaDetalhe);
                    intentDetalhe.putExtra("ingredientes",ingredientesList);
                    intentDetalhe.putExtra("favorito", favorito);

                    startActivity(intentDetalhe);


                } catch (JSONException e) {
                    //e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    //Intent intentLogar = new Intent(ListaReceitasActivity.this, ListaReceitasActivity.class);
                    //startActivity(intentLogar);
                    finish();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();
                        //Toast.makeText(ListaReceitasActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }


    public static ArrayList<String> montalistaIngredientes(JSONArray ingredientes) {

        List<JSONObject> ingJsonObjAux = new ArrayList<JSONObject>();

        List<JSONObject> ingJsonObj = new ArrayList<JSONObject>();

        ArrayList<String> ingredientesList = new ArrayList<String>();

        try {
            for (int i = 0; i < ingredientes.length(); i++) {

                ingJsonObjAux.add(i, ingredientes.getJSONObject(i));

                ingJsonObj.add(i, ingJsonObjAux.get(i).getJSONObject("ingredient"));
                String measure = ingJsonObjAux.get(i).getString("measure");

                ingredientesList.add(i, (measure + " - " + ingJsonObj.get(i).getString("name")));

            }
        }  catch (JSONException e) {
            //e.printStackTrace();


        }

        return ingredientesList;
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public ArrayList<Recipes> preencheReceitas() {
        Intent intent = getIntent();
        if (intent!= null){
            listaReceitas = intent.getParcelableArrayListExtra("receitasList");

        }

        return listaReceitas;
    }

}
