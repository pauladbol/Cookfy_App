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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.livrodereceitas.cookfy.Adapters.GridIngredienteAdapter;
import com.livrodereceitas.cookfy.Classes.Ingrediente;
import com.livrodereceitas.cookfy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PesquisaIngredienteActivity extends AppCompatActivity {
    private static final String REGISTER_URL = "https://cookfy.herokuapp.com/recipes/ingredient?";

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
                    listaIngredientesPesquisa.add(PesquisaIngredienteActivity.this.ingrediente);
                    ingrediente.setText("");
                    baseAdapter.notifyDataSetChanged();
                }
            }
        });


        pesquisar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
              // PesquisaIngrediente();


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
    private boolean validarIngrediente(String ingrediente) {
        String nomePattern = "^[aA-zZ]{2,}+(([ aA-zZ]+)+)?$";
        Pattern pattern = Pattern.compile(nomePattern);
        Matcher matcher = pattern.matcher(ingrediente);
        return matcher.matches();
    }
    /*private void PesquisaIngrediente(){
        String urlListaIngredientes = REGISTER_URL;
        boolean primeira = true;
        for(Ingrediente teste: listaIngredientesPesquisa){
            if (primeira) {
                urlListaIngredientes+= "ingredient=" + teste.getNome();
                primeira = false;
            } else {
                urlListaIngredientes += "&ingredient=" + teste.getNome();
            }
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlListaIngredientes, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                *//*Log.i("script", "id_user "+response.getString("id"));
                id_user = response.getString("id");
                token = response.getString("token");*//*

                Intent intentListaReceitas = new Intent(PesquisaIngredienteActivity.this, ListaReceitasActivity.class);
                startActivity(intentListaReceitas);
                finish();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PesquisaIngredienteActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
    }*/
}
