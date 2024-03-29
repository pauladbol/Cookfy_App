package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Base64;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Adapters.ImagemPagerAdapter;
import com.livrodereceitas.cookfy.Classes.Recipes;
import com.livrodereceitas.cookfy.Classes.User;
import com.livrodereceitas.cookfy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String URL_CATEG = "https://cookfy.herokuapp.com/categories/";
    private static final String URL_RECIPES = "https://cookfy.herokuapp.com/recipes";
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

    private ArrayList<Recipes> receitasList = new ArrayList<Recipes>();

    private int[] imagens = {R.drawable.comidamexicana, R.drawable.comidaitaliana, R.drawable.comidacaseira, R.drawable.comidatailandesa};

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

        Button pesquisa = (Button) findViewById(R.id.pesquisa);
        Button listareceitas = (Button) findViewById(R.id.listareceitas);

        pesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPesq = new Intent(DrawerActivity.this, PesquisaIngredienteActivity.class);
                startActivity(intentPesq);
            }
        });

        listareceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPesqReceita = new Intent(DrawerActivity.this, PesquisaReceitaActivity.class);
                startActivity(intentPesqReceita);

            }
        });
//        User usuario = reqUser();
//        TextView nome = (TextView) this.findViewById(R.id.drawer_nome);
//        TextView email = (TextView) this.findViewById(R.id.drawer_email);
//
//        nome.setText(usuario.getNome());
//        email.setText(usuario.getEmail());

        final ViewPager g = (ViewPager) findViewById(R.id.viewPager);
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

       g.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(final int position) {
               g.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       reqCategorias(position);
                   }
               });

           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });

        //final int currentItem = g.;
        g.setOnClickListener(
                new ViewPager.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        //Intent intentCateg = new Intent(DrawerActivity.this, ListaReceitasActivity.class);

                        //startActivity(intentCateg);

                        reqCategorias(view.getVisibility());

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

        } else if (id == R.id.nav_minhas) {
            reqReceitas("minhas");

        } else if (id == R.id.nav_favoritos) {
            reqReceitas("favoritos");

        } else if (id == R.id.nav_pesquisa) {
            Intent intentPesq = new Intent(DrawerActivity.this, PesquisaIngredienteActivity.class);
            startActivity(intentPesq);

        } else if (id == R.id.nav_pesquisaRecipe) {
            Intent intentPesqRecipe = new Intent(DrawerActivity.this, PesquisaReceitaActivity.class);
            startActivity(intentPesqRecipe);
        } else if (id == R.id.nav_cadastro) {
            Intent intentCad = new Intent(DrawerActivity.this, CadastroReceitaActivity.class);
            startActivity(intentCad);
        } else if (id == R.id.nav_sair) {
            usuarioLogout();
        } else if (id == R.id.nav_receitas){
            reqReceitas("todas");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void reqReceitas(String type){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        urlReq = montaUrl(type,settings.getString("id",""));

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(Request.Method.GET,
                urlReq, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject receitaJSON = response.getJSONObject(i);

                        Recipes receita = new Recipes();
                        receita.setId(receitaJSON.getString("id"));
                        receita.setName(receitaJSON.getString("name"));
                        receita.setDescription(receitaJSON.getString("description"));
                        receita.setExecutionTime(receitaJSON.getString("prepTime"));
                        receita.setDifficulty(receitaJSON.getString("difficulty"));

                        String imgBytes = receitaJSON.getString("picture");
                        if ( imgBytes != "" && imgBytes != "null" ){
                            byte[] imgRecebida = Base64.decode(imgBytes, Base64.DEFAULT);
                            //Bitmap bitNew = BitmapFactory.decodeByteArray(imgRecebida, 0, imgRecebida.length);

                            receita.setImagem2(imgRecebida);
                            }
                        //receita.setDrawableId(R.drawable.imagem);

                        receitasList.add(receita);

                    }

                    Intent intentFav = new Intent(DrawerActivity.this, ListaReceitasActivity.class);
                    intentFav.putParcelableArrayListExtra("receitasList", receitasList);
                    startActivity(intentFav);

                    receitasList.clear();


                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(DrawerActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();
                    }
                }){


        };
        jsonArrayReq.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayReq);
    }

    public String montaUrl(String type,String id) {

        if (type.equals("favoritos")) {
            urlReq = URL_PERFIL + id +"/reacts?react=FAVORITY";

            return urlReq;
        } else if (type.equals("minhas")){
            urlReq = URL_PERFIL + id + "/recipes";

            return urlReq;
        } else {
            return urlReq = URL_RECIPES;
        }
    }

    private void reqCategorias(int position){

        String urlCateg = URL_CATEG + (position+1);

        Log.i("categ", urlCateg);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlCateg, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray recipes = response.getJSONArray("recipes");
                    for (int i = 0; i < recipes.length(); i++) {
                        JSONObject receitaJSON = recipes.getJSONObject(i);

                        Recipes receita = new Recipes();
                        receita.setId(receitaJSON.getString("id"));
                        receita.setName(receitaJSON.getString("name"));
                        receita.setDescription(receitaJSON.getString("description"));
                        receita.setExecutionTime(receitaJSON.getString("prepTime"));
                        receita.setDifficulty(receitaJSON.getString("difficulty"));
                        //receita.setDrawableId(R.drawable.imagem);
                        String imgBytes = receitaJSON.getString("picture");
                        if ( imgBytes != "" && imgBytes != "null" ){
                            byte[] imgRecebida = Base64.decode(imgBytes, Base64.DEFAULT);
                            //Bitmap bitNew = BitmapFactory.decodeByteArray(imgRecebida, 0, imgRecebida.length);

                            receita.setImagem2(imgRecebida);
                        }
                        //receita.setDrawableId(R.drawable.imagem);

                        receitasList.add(receita);

                    }

                    Intent intentFav = new Intent(DrawerActivity.this, ListaReceitasActivity.class);
                    intentFav.putParcelableArrayListExtra("receitasList", receitasList);
                    startActivity(intentFav);

                    receitasList.clear();


                } catch (JSONException e) {
                    //e.printStackTrace();
                    //Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();

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
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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

                    String imagemBytes = response.getString("picture");
                    if (imagemBytes != "null" && imagemBytes != ""){
                        byte[] imagemRecebida = Base64.decode(imagemBytes, Base64.DEFAULT);
                        //Bitmap bitNew = BitmapFactory.decodeByteArray(imgRecebida, 0, imgRecebida.length);

                        usuarioPerfil.setImagem(imagemRecebida);
                    }



                    Intent intentPerfil = new Intent(DrawerActivity.this, PerfilActivity.class);
                    intentPerfil.putExtra("usuario", usuarioPerfil);


                    startActivity(intentPerfil);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(DrawerActivity.this, DrawerActivity.class);
                    startActivity(intentLogar);

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(DrawerActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Ocorreu um erro! Usuário não encontrado.",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

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
