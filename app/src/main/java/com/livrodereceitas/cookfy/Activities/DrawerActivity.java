package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Adapters.ImagemPagerAdapter;
import com.livrodereceitas.cookfy.Classes.Recipes;
import com.livrodereceitas.cookfy.Classes.User;
import com.livrodereceitas.cookfy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String URL_CATEG = "https://cookfy.herokuapp.com/recipes/";
    private static final String URL_PERFIL = "https://cookfy.herokuapp.com/users/";
    public static final String KEY_USERNAME = "user";
    public static final String PREFS_NAME = "MyPrefsFile";


    private String id;
    private String name;
    private String description;
    private String executionTime;
    private String difficulty;
    private String ingredientes;
    private String recipeBooks;
    private String urlReq;

    private List<Recipes> receitasList = new ArrayList<Recipes>();

    private int[] imagens = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        User usuario = reqUser();
//        TextView nome = (TextView) this.findViewById(R.id.drawer_nome);
//        TextView email = (TextView) this.findViewById(R.id.drawer_email);
//
//        nome.setText(usuario.getNome());
//        email.setText(usuario.getEmail());

        ViewPager g = (ViewPager) findViewById(R.id.viewPager);
        g.setAdapter(new ImagemPagerAdapter(this, imagens));

        g.setOnTouchListener(
                new View.OnTouchListener() {
                    private boolean moved;

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            moved = false;
                        }
                        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                            moved = true;
                        }
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            if (!moved) {
                                view.performClick();
                            }
                        }

                        return false;
                    }
                }
        );

        g.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        //reqReceitas("categorias");
                        Intent intentLogar = new Intent(DrawerActivity.this, ListaReceitasActivity.class);
                        startActivity(intentLogar);

                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_perfil) {
            reqPerfil();

        } else if (id == R.id.nav_favoritos) {
            //reqReceitas("favoritos");

        } else if (id == R.id.nav_config) {
            Intent intentConfig = new Intent(DrawerActivity.this, ListaReceitasActivity.class);
            startActivity(intentConfig);

        } else if (id == R.id.nav_sair) {
            usuarioLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void reqReceitas(String type){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (type.equals("favoritos")) { ////    http://cookfy.herokuapp.com/users/{idUser}/reacts?react={FAVORITY|LOVE|LIKE}
            urlReq = URL_PERFIL + settings.getString("id","")+ "reacts?react=FAVORITY";
        } else {
            urlReq = URL_CATEG;
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlReq, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray favoritas = response.getJSONArray("myReacts");

                    for (int i = 0; i < favoritas.length(); i++) {
                        Recipes receita = new Recipes();

                        receita.setName(response.getString("name"));
                    }


                    description = response.getString("description");
                    executionTime = response.getString("executionTime");
                    difficulty = response.getString("difficulty");
                    ingredientes = response.getString("ingredientes");
                    recipeBooks = response.getString("recipeBooks");

                    Toast.makeText(DrawerActivity.this, "!", Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(DrawerActivity.this, ListaReceitasActivity.class);
                    startActivity(intentLogar);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(DrawerActivity.this, DrawerActivity.class);
                    startActivity(intentLogar);
                    finish();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DrawerActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

    private void reqPerfil() {
        final User usuarioPerfil = new User();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        final String urlPerfil = URL_PERFIL + settings.getString("id","");

        Log.i("script", urlPerfil);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlPerfil, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    usuarioPerfil.setNome(response.getString("name"));
                    usuarioPerfil.setUsername(response.getString("username"));
                    usuarioPerfil.setEmail(response.getString("email"));

                    Intent intentPerfil = new Intent(DrawerActivity.this, PerfilActivity.class);
                    intentPerfil.putExtra("usuario", usuarioPerfil);


                    startActivity(intentPerfil);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(DrawerActivity.this, DrawerActivity.class);
                    startActivity(intentLogar);
                    finish();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DrawerActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

//    private User reqUser() {
//        final User usuarioPerfil = new User();
//
//        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//
//        final String urlPerfil = URL_PERFIL + settings.getString("id","");
//
//        Log.i("script", urlPerfil);
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
//                urlPerfil, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//
//                    usuarioPerfil.setNome(response.getString("name"));
//                    usuarioPerfil.setEmail(response.getString("email"));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(DrawerActivity.this,error.toString(),Toast.LENGTH_LONG).show();
//                    }
//                });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonObjReq);
//
//        return usuarioPerfil;
//    }

    private void usuarioLogout() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.remove("token");
        editor.remove("id");

        editor.commit();

        Intent intentSair = new Intent(DrawerActivity.this, MainActivity.class);
        startActivity(intentSair);
    }
}
