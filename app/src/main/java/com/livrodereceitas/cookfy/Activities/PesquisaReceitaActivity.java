package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Classes.Ingrediente;
import com.livrodereceitas.cookfy.Classes.Recipes;
import com.livrodereceitas.cookfy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PesquisaReceitaActivity extends AppCompatActivity {
    private static final String REGISTER_URL = "https://cookfy.herokuapp.com/recipes/search?name=";
    private ArrayList<Recipes> receitasList = new ArrayList<Recipes>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_receita);

        Button pesquisar = (Button) findViewById(R.id.receitaPesquisar);
        final EditText receita = (EditText) findViewById(R.id.receitaNome);
        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesquisaIngrediente(receita.getText().toString());
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void pesquisaIngrediente(String receita){
        String url = REGISTER_URL;
        if(receita != null && receita != ""){
            url+=  receita.replace(" ", "%20");

            JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                    url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    receitasList = montaReceitasPesquisa(response);

                    Intent intentFav = new Intent(PesquisaReceitaActivity.this, ListaReceitasActivity.class);
                    intentFav.putParcelableArrayListExtra("receitasList", receitasList);
                    startActivity(intentFav);
                    finish();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();
                            //Toast.makeText(PesquisaIngredienteActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){


            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjReq);
        }else {
            Toast.makeText(PesquisaReceitaActivity.this,"Você precisa adicionar um prato para fazer a pesquisa",Toast.LENGTH_LONG).show();
        }

    }
    public ArrayList<Recipes> montaReceitasPesquisa(JSONArray respostaWS) {
        ArrayList<Recipes> lista = new ArrayList<Recipes>();

        try {
            for (int i = 0; i < respostaWS.length(); i++) {
                Log.i("fav", "FAV " + respostaWS.toString());

                JSONObject receitaJSON = respostaWS.getJSONObject(i);

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

                lista.add(receita);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return lista;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
