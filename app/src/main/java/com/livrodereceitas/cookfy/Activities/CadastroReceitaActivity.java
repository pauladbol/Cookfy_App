package com.livrodereceitas.cookfy.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
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
import com.google.gson.JsonObject;
import com.livrodereceitas.cookfy.Adapters.AdapterStepsReceita;
import com.livrodereceitas.cookfy.Adapters.GridIngredienteAdapter;
import com.livrodereceitas.cookfy.Classes.Ingrediente;
import com.livrodereceitas.cookfy.Classes.RecipeStep;
import com.livrodereceitas.cookfy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
    private static final int CODIGO_CAMERA = 567;
    private static final String PREFS_NAME = "MyPrefsFile";

    private String[] dificuldade;
    private ArrayList<Ingrediente> listaIngredientesReceita = new ArrayList<Ingrediente>();
    private ArrayList<RecipeStep> listaStepsReceita = new ArrayList<>();
    private RecipeStep recipeStep;
    private Ingrediente ingrediente;
    private Spinner spinnerDificuldade;
    private String dificuldadeReceita;
    private EditText nomeReceita;
    private EditText ingredienteRecitaNome;
    private EditText ingredienteReceitaMedida;
    private EditText tempoPreparo;
    private EditText tempoForno;
    private Button camera;
    private String caminhoFoto;
    private Integer stepOrder = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_receita);

        camera = (Button) findViewById(R.id.camera);
        Button adicionar = (Button) findViewById(R.id.ingredienteReceitaAdicionar);
        Button adicionarStepsReceita = (Button) findViewById(R.id.stepReceitaAdicionar);
        Button salvar = (Button)findViewById(R.id.salvarReceita);
        // final GridView gridView = (GridView) findViewById(R.id.gridIngredientesReceitas);

        final ListView listView =(ListView) findViewById(R.id.gridIngredientesReceitas);
        final ListView listViewSteps = (ListView) findViewById(R.id.listStepsReceita);

        //final ArrayAdapter<RecipeStep> adpStepsRecipes = new ArrayAdapter<RecipeStep>(this, android.R.layout.simple_list_item_1, listaStepsReceita);
        final BaseAdapter baseAdapter = new GridIngredienteAdapter(this, listaIngredientesReceita);
        final BaseAdapter baseAdapterStepRecipe = new AdapterStepsReceita(this, listaStepsReceita);
        dificuldade = new String[]{"Dificuldade", "EASY", "MEDIUM", "HARD"};
        ArrayAdapter<String> adpDificuldade = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dificuldade);

        nomeReceita = (EditText) findViewById(R.id.nomeReceita);
        spinnerDificuldade = (Spinner) findViewById(R.id.dificuldadeReceita);
        tempoForno = (EditText) findViewById(R.id.tempoFornoReceita);
        tempoPreparo = (EditText) findViewById(R.id.tempoPreparoReceita);

        adpDificuldade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDificuldade.setAdapter(adpDificuldade);

        spinnerDificuldade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dificuldadeReceita = dificuldade[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CadastroReceitaActivity.this, "Selections cleared.", Toast.LENGTH_SHORT).show();

            }

        });

        adicionarStepsReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeStep = new RecipeStep();
                EditText stepReceitaDescription = (EditText) findViewById(R.id.stepReceita);

                CadastroReceitaActivity.this.recipeStep.setDescription(stepReceitaDescription.getText().toString());
                CadastroReceitaActivity.this.recipeStep.setStepOrder(stepOrder+=1);

                listaStepsReceita.add(CadastroReceitaActivity.this.recipeStep);
                stepReceitaDescription.setText("");

                baseAdapterStepRecipe.notifyDataSetChanged();
                listViewSteps.setAdapter(baseAdapterStepRecipe);
                setListViewHeightBasedOnItems(listViewSteps);
            }
        });
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingrediente = new Ingrediente();
                ingredienteRecitaNome = (EditText) findViewById(R.id.ingredienteReceitaNome);
                ingredienteReceitaMedida = (EditText) findViewById(R.id.ingredienteReceitaMedida);

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
                listView.setAdapter(baseAdapter);
                setListViewHeightBasedOnItems(listView);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ingrediente = (Ingrediente) listView.getItemAtPosition(position);
                listaIngredientesReceita.remove(position);
                baseAdapter.notifyDataSetChanged();
                Toast.makeText(CadastroReceitaActivity.this, "ingrediente " + ingrediente.getNome() + " apagado", Toast.LENGTH_SHORT).show();
                setListViewHeightBasedOnItems(listView);
                return true;
            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastrarReceita(listaIngredientesReceita);
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) +  "/" + System.currentTimeMillis() +".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));

                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_CAMERA) {
                ImageView foto = (ImageView) findViewById(R.id.imagemReceita);
                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                foto.setScaleType(ImageView.ScaleType.CENTER_CROP);
                foto.setImageBitmap(bitmapReduzido);
            }
        }
    }

    private boolean validarIngrediente(String ingrediente) {
        String nomePattern = "^[aA-zZ]{2,}+(([ aA-zZ]+)+)?$";
        Pattern pattern = Pattern.compile(nomePattern);
        Matcher matcher = pattern.matcher(ingrediente);
        return matcher.matches();
    }
    private void CadastrarReceita(ArrayList<Ingrediente> ingredientesLista){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final JSONObject jsonobj = new JSONObject();
        final String nome = nomeReceita.getText().toString().trim();
        String tempoPreparoReceitaString = tempoPreparo.getText().toString().trim();
        String tempoFornoReceitaString = tempoForno.getText().toString().trim();
        Integer tempoFornoReceita = Integer.parseInt(tempoFornoReceitaString);
        Integer tempoPreparoReceita = Integer.parseInt(tempoPreparoReceitaString);
        Integer cheffId = Integer.parseInt(settings.getString("id", ""));

        JSONArray modoPreparoArray = new JSONArray();
        JSONArray ingredientesArray = new JSONArray();
        for(int i=0; i<ingredientesLista.size();i++){
            ingredientesArray.put(ingredientesLista.get(i).getNome());
        }
        for(RecipeStep recipeStep : listaStepsReceita){
            final JSONObject jsonSteps = new JSONObject();
            try {
                jsonSteps.put("stepOrder", recipeStep.getStepOrder());
                jsonSteps.put("description", recipeStep.getDescription());

                modoPreparoArray.put(jsonSteps);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            jsonobj.put(KEY_NAME, nome);
            jsonobj.put(KEY_DIFFICULTY, dificuldadeReceita);
            jsonobj.put(KEY_STEP, modoPreparoArray);
            jsonobj.put(KEY_INGREDIENT, ingredientesArray);
            jsonobj.put(KEY_COOKTIME, tempoFornoReceita);
            jsonobj.put(KEY_PREPTIME, tempoPreparoReceita);
            jsonobj.put(KEY_CHEF, cheffId);
            jsonobj.put(KEY_CATEGORY, 1);


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
                Intent intentCadastrar = new Intent(CadastroReceitaActivity.this, DrawerActivity.class);
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
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {

                float px = 300 * (listView.getResources().getDisplayMetrics().density);

                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(View.MeasureSpec.makeMeasureSpec((int)px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() * (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}


//android:stretchMode="columnWidth"
//android:numColumns="3"