package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        String urlListaIngredientes = "";
        if(listaIngredientesPesquisa != null){
            urlListaIngredientes = montaUrlIngredientes(listaIngredientesPesquisa);

            JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                    urlListaIngredientes, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                        receitasList = montaReceitasPesquisa(response);

                        Intent intentFav = new Intent(PesquisaIngredienteActivity.this, ListaReceitasActivity.class);
                        intentFav.putParcelableArrayListExtra("receitasList", receitasList);
                        startActivity(intentFav);
                        finish();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde. " + error,Toast.LENGTH_LONG).show();
                            //Toast.makeText(PesquisaIngredienteActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){


            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjReq);
        }else {
            Toast.makeText(PesquisaIngredienteActivity.this,"Você precisa adicionar um ingrediente para fazer a pesquisa",Toast.LENGTH_LONG).show();
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


    public String montaUrlIngredientes(List<Ingrediente> listaIngredientesPesquisa) {
        String urlIngredientes = REGISTER_URL;

        boolean primeira = true;
        for(Ingrediente teste: listaIngredientesPesquisa){
            if (primeira) {
                urlIngredientes += "ingredient=" + teste.getNome().replace(" ", "%20");
                primeira = false;
            } else {
                urlIngredientes += "&ingredient=" + teste.getNome().replace(" ", "%20");
            }
        }

        return urlIngredientes;
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
