package com.livrodereceitas.cookfy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CadastrarReceitaActivity extends AppCompatActivity {

    private LinearLayout ll;
    private int count = 0;
    private ArrayList<EditText> edits;
    private TextView textoEdits;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_receita);

        edits = new ArrayList<EditText>();
        ll = (LinearLayout) findViewById(R.id.edits_ll);
        //textoEdits = (TextView) findViewById(R.id.showvalues);
        Button btn_create = (Button) findViewById(R.id.criar);
        btn_create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                criarNovaEditText();
            }
        });

    }

    protected void criarNovaEditText() {
        count++;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        EditText edit = new EditText(this);
        edit.setHint("Ingrediente" + count);
        edits.add(edit); // adiciona a nova editText a lista.
        ll.addView(edit, params); // adiciona a editText ao ViewGroup

    }

    private void calcular() {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < edits.size(); i++) {
            texto.append(edits.get(i).getText().toString());
        }
        textoEdits.setText(texto);
    }
}
