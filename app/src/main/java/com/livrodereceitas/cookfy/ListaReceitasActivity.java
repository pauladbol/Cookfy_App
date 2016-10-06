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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
//AppCompatActivity
public class ListaReceitasActivity extends Activity {
    private static final String REGISTER_URL = "localhost:8080/cookfy/recipes/";
    public static final String KEY_USERNAME = "user";
    public static final String KEY_PASSWORD = "hash";
    public static final String KEY_ADAPTER = "adapter";

    private String id;
    private String name;
    private String description;
    private String executionTime;
    private String difficulty;
    private String ingredientes;
    private String recipeBooks;

    private String idReceita;

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

    private void pegaReceitas(){
        final String urlReceita = REGISTER_URL + idReceita;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlReceita, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    id = response.getString("id");
                    name = response.getString("name");
                    description = response.getString("description");
                    executionTime = response.getString("executionTime");
                    difficulty = response.getString("difficulty");
                    ingredientes = response.getString("ingredientes");
                    recipeBooks = response.getString("recipeBooks");

                    Toast.makeText(ListaReceitasActivity.this, "Bem vindo!", Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(ListaReceitasActivity.this, DetalheActivity.class);
                    startActivity(intentLogar);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(ListaReceitasActivity.this, ListaReceitasActivity.class);
                    startActivity(intentLogar);
                    finish();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListaReceitasActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }
}
