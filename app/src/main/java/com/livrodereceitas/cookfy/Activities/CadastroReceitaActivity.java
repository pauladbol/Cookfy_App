package com.livrodereceitas.cookfy.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.livrodereceitas.cookfy.Adapters.GridIngredienteAdapter;
import com.livrodereceitas.cookfy.Classes.Ingrediente;
import com.livrodereceitas.cookfy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroReceitaActivity extends AppCompatActivity {
    private List<Ingrediente> listaIngredientesReceita = new ArrayList<Ingrediente>();
    Ingrediente ingrediente;
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
        Spinner spinnerDificuldade = (Spinner) findViewById(R.id.dificuldadeReceita);
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
}


//android:stretchMode="columnWidth"
//android:numColumns="3"