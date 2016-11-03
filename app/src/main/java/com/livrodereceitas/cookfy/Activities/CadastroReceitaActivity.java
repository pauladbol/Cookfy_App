package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Adapters.GridIngredienteAdapter;
import com.livrodereceitas.cookfy.Classes.Ingrediente;
import com.livrodereceitas.cookfy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroReceitaActivity extends AppCompatActivity {
    public static final String REGISTER_URL = "https://cookfy.herokuapp.com/recipes";
    public static final String KEY_NAME = "recipe_name";
    public static final String KEY_CHEF = "chef_id";
    public static final String KEY_DIFFICULTY= "difficulty";
    public static final String KEY_PREPTIME = "prepTime";
    public static final String KEY_COOKTIME= "cookTime";
    public static final String KEY_CATEGORY= "category_id";
    public static final String KEY_STEP= "recipeStep";
    public static final String KEY_INGREDIENT= "ingredient_measure";

    private List<Ingrediente> listaIngredientesReceita = new ArrayList<Ingrediente>();
    Ingrediente ingrediente;
    Spinner spinnerDificuldade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_receita);
        Button adicionar = (Button) findViewById(R.id.ingredienteReceitaAdicionar);

        // final GridView gridView = (GridView) findViewById(R.id.gridIngredientesReceitas);

        final ListView listView =(ListView) findViewById(R.id.gridIngredientesReceitas);
        final BaseAdapter baseAdapter = new GridIngredienteAdapter(this, listaIngredientesReceita);

        final String[] dificuldade = {"Dificuldade", "Tetinha", "Vai, mas não vai muito", "Fudeu", "É o capeta"};

        ArrayAdapter<String> adpDificuldade = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dificuldade);
        spinnerDificuldade = (Spinner) findViewById(R.id.dificuldadeReceita);
        adpDificuldade.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerDificuldade.setAdapter(adpDificuldade);

        spinnerDificuldade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CadastroReceitaActivity.this, "Selection: "+dificuldade[position], Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CadastroReceitaActivity.this, "Selections cleared.", Toast.LENGTH_SHORT).show();

            }

        });
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingrediente = new Ingrediente();
                final EditText ingredienteRecitaNome = (EditText) findViewById(R.id.ingredienteReceitaNome);
                EditText ingredienteReceitaMedida = (EditText) findViewById(R.id.ingredienteReceitaMedida);

                if (!validarIngrediente(ingredienteRecitaNome.getText().toString())) {
                    ingredienteRecitaNome.setError("O campo ingrediente não pode ser vazio");
                    ingredienteRecitaNome.requestFocus();}
                else {

                    CadastroReceitaActivity.this.ingrediente.setNome(ingredienteReceitaMedida.getText().toString() + ";" + ingredienteRecitaNome.getText().toString());

                    listaIngredientesReceita.add(CadastroReceitaActivity.this.ingrediente);
                    ingredienteRecitaNome.setText("");
                    ingredienteReceitaMedida.setText("");
                    baseAdapter.notifyDataSetChanged();
                }
            }
        });

        listView.setAdapter(baseAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ingrediente = (Ingrediente) listView.getItemAtPosition(position);
                listaIngredientesReceita.remove(position);
                baseAdapter.notifyDataSetChanged();
                Toast.makeText(CadastroReceitaActivity.this, "ingrediente " + ingrediente.getNome() + " apagado", Toast.LENGTH_SHORT).show();
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
    /*private void CadastrarReceita(){
        final JSONObject jsonobj = new JSONObject();

        final String adapter = "application";
        final String name = nome.getText().toString().trim();
        final String username = usuario.getText().toString().trim();
        final String password = senha.getText().toString().trim();
        final String emaill = email.getText().toString().trim();
        final Date date = new Date();


        try {
            jsonobj.put(KEY_NAME,name);
            jsonobj.put(KEY_CHEF,username);
            jsonobj.put(KEY_DIFFICULTY, emaill);
            //jsonobj.put(KEY_STEP,passwordHash);
            jsonobj.put(KEY_INGREDIENT,listaIngredientesReceita);

//            jsonobj.put(KEY_DATEC,date);
//            jsonobj.put(KEY_DATEU,date);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"123456789: " + e.getMessage(),Toast.LENGTH_LONG).show();
        }

        Log.i("script", jsonobj.toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                REGISTER_URL, jsonobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i("script", "entrou no request!!");
                Toast.makeText(CadastroActivity.this, "Bem vindo!", Toast.LENGTH_LONG).show();
                Intent intentLogar = new Intent(CadastroActivity.this, LoginNovoActivity.class);
                startActivity(intentLogar);
                finish();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroActivity.this,"!!"+error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }*/
}


//android:stretchMode="columnWidth"
//android:numColumns="3"