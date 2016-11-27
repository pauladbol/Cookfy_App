package com.livrodereceitas.cookfy.Helpers;

import android.widget.ImageView;
import android.widget.TextView;

import com.livrodereceitas.cookfy.Activities.DetalheActivity;
import com.livrodereceitas.cookfy.R;
import com.livrodereceitas.cookfy.Classes.Recipes;

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
        fotoReceita = (ImageView) activity.findViewById(R.id.imagem);
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


}
