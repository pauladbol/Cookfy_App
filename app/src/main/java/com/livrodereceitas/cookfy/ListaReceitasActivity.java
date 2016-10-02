package com.livrodereceitas.cookfy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListaReceitasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receitas);

        List<Recipes> list = new ArrayList<Recipes>();

        list.add(new Recipes(1, "Lorem ipsum"));
    }
}
