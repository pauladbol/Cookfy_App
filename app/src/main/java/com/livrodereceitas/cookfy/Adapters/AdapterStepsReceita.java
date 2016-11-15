package com.livrodereceitas.cookfy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.livrodereceitas.cookfy.Classes.Ingrediente;
import com.livrodereceitas.cookfy.Classes.RecipeStep;
import com.livrodereceitas.cookfy.R;

import java.util.List;

/**
 * Created by Thiago on 13/11/2016.
 */
public class AdapterStepsReceita extends BaseAdapter{
    private List<RecipeStep> listaReceipeSteps;
    private final LayoutInflater mInflater;

    public AdapterStepsReceita(Context context, List<RecipeStep> recipeSteps) {
        mInflater = LayoutInflater.from(context);
        listaReceipeSteps = recipeSteps;

    }

    @Override
    public int getCount() {
        return listaReceipeSteps.size();
    }

    @Override
    public RecipeStep getItem(int position) {
        return listaReceipeSteps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView description;
        if (v == null) {
            v = mInflater.inflate(R.layout.activity_grid_item, parent, false);
            v.setTag(R.id.ingrediente, v.findViewById(R.id.ingrediente));
        }
        description = (TextView) v.getTag(R.id.ingrediente);
        RecipeStep recipeStep = (RecipeStep) getItem(position);
        int pos = recipeStep.getDescription().indexOf(";");
        description.setText(recipeStep.getDescription().substring(pos+1, recipeStep.getDescription().length()));

        return v;
    }
}
