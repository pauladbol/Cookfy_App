package com.livrodereceitas.cookfy;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Asus on 05/10/2016.
 */
public class DetalheHelper {
    TextView nomeReceita;
    ImageView fotoReceita;
    TextView ingredientesReceita;
    TextView modoPreparoReceita;
    private Recipes receita;
    public DetalheHelper(DetalheActivity activity){
        nomeReceita = (TextView) activity.findViewById(R.id.nome_receita);
        fotoReceita = (ImageView) activity.findViewById(R.id.test);
        ingredientesReceita= (TextView) activity.findViewById(R.id.descricao1);
        modoPreparoReceita= (TextView) activity.findViewById(R.id.descricao);
        receita = new Recipes();
    }

    public Recipes pegaReceita(){;

      /*  receita.setName(nomeReceita.getText().toString());
        receita.setDescription(modoPreparoReceita.getText().toString());

        receita.setDrawableId(fotoReceita.getDrawable());*/
        return receita;
    }

    public void preencheDetalhe(Recipes receita) {
        nomeReceita.setText(receita.getName());
        modoPreparoReceita.setText(receita.getDescription());
        ingredientesReceita.setText(receita.getIngredientes());
        fotoReceita.setImageResource(receita.getDrawableId());
        this.receita = receita;
    }
}
