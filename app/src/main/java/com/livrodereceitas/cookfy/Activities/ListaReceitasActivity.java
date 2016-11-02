package com.livrodereceitas.cookfy.Activities;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Adapters.GridViewAdapter;
import com.livrodereceitas.cookfy.R;
import com.livrodereceitas.cookfy.Classes.Recipes;

import org.json.JSONException;
import org.json.JSONObject;

//AppCompatActivity
public class ListaReceitasActivity extends AppCompatActivity {

    private static final String REGISTER_URL = "https://cookfy.herokuapp.com/recipes/";

    Recipes receita = new Recipes();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receitas);


        final GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new GridViewAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                receita = (Recipes) gridView.getItemAtPosition(position);

                pegaReceita(receita);

            }
        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.list_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
//        navigationView.setNavigationItemSelectedListener(this);
    }

    private void pegaReceita(final Recipes receitaDetalhe){
        final String urlReceita = REGISTER_URL + receitaDetalhe.getId();
        Log.i("script", "1");
        //Map<String,String> params = new HashMap<String, String>();
        //params.put("recId","1");
        Log.i("script", "2");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlReceita, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    receitaDetalhe.setName(response.getString("name"));
                    receitaDetalhe.setDescription(response.getString("description"));


                    Log.i("script", receitaDetalhe.getName());
                    Intent intentDetalhe = new Intent(ListaReceitasActivity.this, DetalheActivity.class);
                    intentDetalhe.putExtra("receita", receitaDetalhe);
                    startActivity(intentDetalhe);


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
        Log.i("script", "3");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.drawer, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//
//        if (id == R.id.nav_perfil) {
//            Intent intentPerfil = new Intent(ListaReceitasActivity.this, PerfilActivity.class);
//            startActivity(intentPerfil);
//        } else if (id == R.id.nav_favoritos) {
//            reqReceitas("favoritos");
//
//        } else if (id == R.id.nav_config) {
//            Intent intentConfig = new Intent(ListaReceitasActivity.this, ListaReceitasActivity.class);
//            startActivity(intentConfig);
//
//        } else if (id == R.id.nav_sair) {
//            usuarioLogout();
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//
//
//
//        return true;
//    }
//
//
//    private void reqReceitas(String type){
//        if (type.equals("favoritos")) {
//            urlReq = REGISTER_URL_FAV;
//        } else {
//            urlReq = REGISTER_URL_CATEG;
//        }
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
//                urlReq, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    id = response.getString("id");
//                    name = response.getString("name");
//                    description = response.getString("description");
//                    executionTime = response.getString("executionTime");
//                    difficulty = response.getString("difficulty");
//                    ingredientes = response.getString("ingredientes");
//                    recipeBooks = response.getString("recipeBooks");
//
//                    Toast.makeText(ListaReceitasActivity.this, "!", Toast.LENGTH_LONG).show();
//                    Intent intentLogar = new Intent(ListaReceitasActivity.this, ListaReceitasActivity.class);
//                    startActivity(intentLogar);
//                    finish();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
//                    Intent intentLogar = new Intent(ListaReceitasActivity.this, DrawerActivity.class);
//                    startActivity(intentLogar);
//                    finish();
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ListaReceitasActivity.this,error.toString(),Toast.LENGTH_LONG).show();
//                    }
//                }){
//
//
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonObjReq);
//    }
//
//    private void usuarioLogout() {
//
//        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//        SharedPreferences.Editor editor = settings.edit();
//
//        editor.remove("token");
//        editor.remove("id");
//
//        editor.commit();
//
//        Intent intentSair = new Intent(ListaReceitasActivity.this, MainActivity.class);
//        startActivity(intentSair);
//    }

}
