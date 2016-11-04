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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Adapters.GridIngredienteAdapter;
import com.livrodereceitas.cookfy.Classes.Ingrediente;
import com.livrodereceitas.cookfy.R;

import org.json.JSONArray;
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
    private Ingrediente ingrediente;
    private Spinner spinnerDificuldade;
    private String dificuldadeReceita;
    private EditText nomeReceita;
    private EditText modoPreparo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_receita);
        Button adicionar = (Button) findViewById(R.id.ingredienteReceitaAdicionar);
        Button salvar = (Button)findViewById(R.id.salvarReceita);
        // final GridView gridView = (GridView) findViewById(R.id.gridIngredientesReceitas);

        final ListView listView =(ListView) findViewById(R.id.gridIngredientesReceitas);
        final BaseAdapter baseAdapter = new GridIngredienteAdapter(this, listaIngredientesReceita);
        final String[] dificuldade = {"Dificuldade", "EASY", "MEDIUM", "HARD"};
        nomeReceita = (EditText) findViewById(R.id.nomeReceita);
        modoPreparo = (EditText) findViewById(R.id.modoPreparoReceita);
        ArrayAdapter<String> adpDificuldade = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dificuldade);
        spinnerDificuldade = (Spinner) findViewById(R.id.dificuldadeReceita);
        adpDificuldade.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerDificuldade.setAdapter(adpDificuldade);

        spinnerDificuldade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CadastroReceitaActivity.this, "Selection: "+dificuldade[position], Toast.LENGTH_SHORT).show();
                dificuldadeReceita = dificuldade[position];
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

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // CadastrarReceita(listaIngredientesReceita);
            }
        });

    }
    private boolean validarIngrediente(String ingrediente) {
        String nomePattern = "^[aA-zZ]{2,}+(([ aA-zZ]+)+)?$";
        Pattern pattern = Pattern.compile(nomePattern);
        Matcher matcher = pattern.matcher(ingrediente);
        return matcher.matches();
    }
    private void CadastrarReceita(List<Ingrediente> ingredientesLista){
        final JSONObject jsonobj = new JSONObject();

        final String nome = nomeReceita.getText().toString().trim();
        final List<String> modoPreparoReceita = new ArrayList<>();
        modoPreparoReceita.add(modoPreparo.getText().toString().trim());

        JSONArray ingredienteArray = new JSONArray(ingredientesLista);
        JSONArray modoPreparoArray = new JSONArray(modoPreparoReceita);
        try {
            jsonobj.put(KEY_NAME, nome);
            jsonobj.put(KEY_DIFFICULTY, dificuldadeReceita);
            jsonobj.put(KEY_STEP, modoPreparoArray);
            jsonobj.put(KEY_INGREDIENT, ingredienteArray);


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
                Toast.makeText(CadastroReceitaActivity.this, "Receita salva com sucesso!", Toast.LENGTH_LONG).show();
                Intent intentCadastrar = new Intent(CadastroReceitaActivity.this, Main2Activity.class);
                startActivity(intentCadastrar);
                finish();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroReceitaActivity.this,"!!"+error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);

    }
}


//android:stretchMode="columnWidth"
//android:numColumns="3"