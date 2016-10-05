package com.livrodereceitas.cookfy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends BaseAdapter {
    private final List<Recipes> receita = new ArrayList<Recipes>();
    private final LayoutInflater mInflater;

    public Adaptador(Context context) {
        mInflater = LayoutInflater.from(context);

        receita.add(new Recipes(1, "Almondegas", R.drawable.img1));
        receita.add(new Recipes(2, "Lazanha", R.drawable.img2));
        receita.add(new Recipes(3, "Bolo Chocolate", R.drawable.img3));
        receita.add(new Recipes(4, "Panquecas", R.drawable.img4));
        receita.add(new Recipes(5, "Almondegas", R.drawable.img1));
        receita.add(new Recipes(6, "Lazanha", R.drawable.img2));
        receita.add(new Recipes(7, "Bolo Chocolate", R.drawable.img3));
        receita.add(new Recipes(9, "Panquecas", R.drawable.img4));
        receita.add(new Recipes(10, "Almondegas", R.drawable.img1));
        receita.add(new Recipes(11, "Lazanha", R.drawable.img2));
        receita.add(new Recipes(12, "Bolo Chocolate", R.drawable.img3));
        receita.add(new Recipes(13, "Panquecas", R.drawable.img4));
    }

    @Override
    public int getCount() {
        return receita.size();
    }




    @Override
    public Recipes getItem(int i) {
        return receita.get(i);
    }

    @Override
    public long getItemId(int i) {
        return receita.get(i).getDrawableId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;


        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));

        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);


        Recipes receita = getItem(i);

        picture.setImageResource(receita.getDrawableId());
        name.setText(receita.getName());


        return v;
    }


}