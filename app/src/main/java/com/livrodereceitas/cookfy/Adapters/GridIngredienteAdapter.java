package com.livrodereceitas.cookfy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.livrodereceitas.cookfy.Classes.Ingrediente;
import com.livrodereceitas.cookfy.R;

import java.util.List;

/**
 * Created by Asus on 20/10/2016.
 */
public class GridIngredienteAdapter extends BaseAdapter {
    private List<Ingrediente> ListaingredientesPesquisa ;
    private final LayoutInflater mInflater;

    public GridIngredienteAdapter(Context context, List<Ingrediente> ingredientes) {
        mInflater = LayoutInflater.from(context);
        ListaingredientesPesquisa = ingredientes;

    }

    @Override
    public int getCount() {
        return ListaingredientesPesquisa.size();
    }

    @Override
    public Ingrediente getItem(int position) {
        return ListaingredientesPesquisa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView nome;
        if (v == null) {
            v = mInflater.inflate(R.layout.activity_grid_item, parent, false);
            v.setTag(R.id.ingrediente, v.findViewById(R.id.ingrediente));
        }
        nome = (TextView) v.getTag(R.id.ingrediente);
        Ingrediente ingrediente = (Ingrediente) getItem(position);
        nome.setText(ingrediente.getNome());

        return v;
    }
}
