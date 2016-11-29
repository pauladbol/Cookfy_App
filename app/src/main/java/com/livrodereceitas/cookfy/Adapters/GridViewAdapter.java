package com.livrodereceitas.cookfy.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.livrodereceitas.cookfy.R;
import com.livrodereceitas.cookfy.Classes.Recipes;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<Recipes> listaReceita;
    private final LayoutInflater mInflater;

    public GridViewAdapter(Context context, ArrayList<Recipes> receita) {
        mInflater = LayoutInflater.from(context);
        listaReceita = receita;
    }

    @Override
    public int getCount() {
        return listaReceita.size();
    }

    @Override
    public Recipes getItem(int i) {
        return listaReceita.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaReceita.get(i).getDrawableId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;
       // TextView ingredientes;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
            //v.setTag(R.id.descricao1, v.findViewById(R.id.descricao1));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);
        //ingredientes = (TextView) v.getTag(R.id.descricao1);


        Recipes receita = getItem(i);

        if (receita.getImagem2()!=null && receita.getImagem2().length>0) {
            Bitmap bitNew = BitmapFactory.decodeByteArray(receita.getImagem2(), 0, receita.getImagem2().length);
            picture.setImageBitmap(bitNew);
        } else {
            picture.setImageResource(R.drawable.imagem);
        }

        //picture.setImageResource(receita.getDrawableId());
        name.setText(receita.getName());
        //ingredientes.setText(receita.getIngredientes());

        return v;
    }


}




























