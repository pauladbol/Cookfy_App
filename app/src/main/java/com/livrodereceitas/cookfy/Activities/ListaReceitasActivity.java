package com.livrodereceitas.cookfy.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.livrodereceitas.cookfy.GsonRequest;
import com.livrodereceitas.cookfy.R;
import com.livrodereceitas.cookfy.Classes.Recipes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.livrodereceitas.cookfy.Classes.Recipes;

//AppCompatActivity
public class ListaReceitasActivity extends Activity {
    private static final String REGISTER_URL = "https://cookfy.herokuapp.com/recipes/";
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

    Recipes receita = new Recipes();
    private Recipes receitaTeste;

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

                pegaReceita();

               // Toast.makeText(ListaReceitasActivity.this, "receita" + receita.getName(), Toast.LENGTH_SHORT).show();
                Intent intentVaiPraDetalheReceita = new Intent(ListaReceitasActivity.this, DetalheActivity.class);
                intentVaiPraDetalheReceita.putExtra("receita", receita);
                startActivity(intentVaiPraDetalheReceita);
            }
        });
    }

    private void pegaReceita(){
        final String urlReceita = REGISTER_URL +"1" /*receita.getId()*/ ;
        Log.i("script", "1");
        Map<String,String> params = new HashMap<String, String>();
        params.put("recId","1");
        Log.i("script", "2");

        // GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
        //       Response.Listener<T> listener, Response.ErrorListener errorListener

        GsonRequest gsonRequest = new GsonRequest(urlReceita, Recipes.class, params, new Response.Listener() {
            @Override
            public void onResponse(Object response) {

                Log.i("script", "receitaid");
                Toast.makeText(ListaReceitasActivity.this, "1!", Toast.LENGTH_LONG).show();
                Intent intentDetalhe = new Intent(ListaReceitasActivity.this, DetalheActivity.class);
                startActivity(intentDetalhe);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error: " + error.getMessage(),Toast.LENGTH_LONG).show();
                Intent intentLogar = new Intent(ListaReceitasActivity.this, ListaReceitasActivity.class);
                startActivity(intentLogar);
                finish();
            }
        });



//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
//                urlReceita, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//
//                    receita.setName(response.getString("name"));
//                    receita.setDescription(response.getString("description"));
//
//                    Toast.makeText(ListaReceitasActivity.this, "1!", Toast.LENGTH_LONG).show();
//                    Intent intentDetalhe = new Intent(ListaReceitasActivity.this, DetalheActivity.class);
//                    startActivity(intentDetalhe);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
//                    Intent intentLogar = new Intent(ListaReceitasActivity.this, ListaReceitasActivity.class);
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
        Log.i("script", "3");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(gsonRequest);
    }
}
