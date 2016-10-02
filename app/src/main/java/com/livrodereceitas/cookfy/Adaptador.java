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
    private final List<Item> receita = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public Adaptador(Context context) {
        mInflater = LayoutInflater.from(context);

        receita.add(new Item("Almondegas",       R.drawable.img1));
        receita.add(new Item("Lazanha",   R.drawable.img2));
        receita.add(new Item("Bolo Chocolate", R.drawable.img3));
        receita.add(new Item("Panquecas",      R.drawable.img4));
        receita.add(new Item("Almondegas",       R.drawable.img1));
        receita.add(new Item("Lazanha",   R.drawable.img2));
        receita.add(new Item("Bolo Chocolate", R.drawable.img3));
        receita.add(new Item("Panquecas",      R.drawable.img4));
        receita.add(new Item("Almondegas",       R.drawable.img1));
        receita.add(new Item("Lazanha",   R.drawable.img2));
        receita.add(new Item("Bolo Chocolate", R.drawable.img3));
        receita.add(new Item("Panquecas",      R.drawable.img4));
    }

    @Override
    public int getCount() {
        return receita.size();
    }

    @Override
    public Item getItem(int i) {
        return receita.get(i);
    }

    @Override
    public long getItemId(int i) {
        return receita.get(i).drawableId;
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

        Item item = getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}