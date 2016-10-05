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

        receita.add(new Recipes(1, "Almondegas", R.drawable.img1, " Misture a carne com o ovo, a cebola, o sal, um pouco de azeite de oliva (ou óleo) e a pimenta\n" +
                "Agregue a farinha até dar o ponto de enrolar as almôndegas\n" +
                "Faça pequenas bolinhas\n" +
                "Em uma panela com um pouco de azeite, frite as almôndegas selando-as em fogo alto\n" +
                "Retire as almôndegas e reserve\n" +
                "Em outra panela, esquente o molho de tomate em fogo baixo\n" +
                "Na mesma panela da almôndega, elimine o excesso de azeite e coloque o molho de tomate, colocando as almôndegas para cozinhar por alguns minutos\n" +
                "Em cerca de 15 minutos as almôndegas estarão totalmente cozidas e o prato estará pronto"));
        receita.add(new Recipes(2, "Lazanha", R.drawable.img2, "Asse bem os peitos de frango já temperados com sal, condimentos do tipo sazón, etc\n" +
                "Acrescente algumas ervas finas no cozimento\n" +
                "Reserve a água que sobrar do cozimento\n" +
                "Tire o osso do peito, o restante você pode optar por desfiar na mão ou bater na batedeira que desfia bem mais rápido\n" +
                "Pegue os temperos o tomate, o pimentão e a cebola\n" +
                "Corte em pedaços bem pequenos\n" +
                "Coloque junto ao frango já desfiado na panela com a água reservada\n" +
                "Ferva até que os temperos dissolvam, e fique um cozidinho bem gostoso"));
        receita.add(new Recipes(3, "Bolo Chocolate", R.drawable.img3, "Bata bem todos os ingredientes da massa (menos o fermento) no liquidificador, aproximadamente 2 a 3 minutos\n" +
                "Acrescente o fermento e bata por mais uns 15 segundos\n" +
                "Coloque em uma forma redonda, untada com manteiga e polvilhada com farinha de trigo\n" +
                "Asse por cerca de 40 minutos em forno médio (180º C), preaquecido"));
        receita.add(new Recipes(4, "Panquecas", R.drawable.img4, "Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        receita.add(new Recipes(5, "Almondegas", R.drawable.img1, " Misture a carne com o ovo, a cebola, o sal, um pouco de azeite de oliva (ou óleo) e a pimenta\n" +
                "Agregue a farinha até dar o ponto de enrolar as almôndegas\n" +
                "Faça pequenas bolinhas\n" +
                "Em uma panela com um pouco de azeite, frite as almôndegas selando-as em fogo alto\n" +
                "Retire as almôndegas e reserve\n" +
                "Em outra panela, esquente o molho de tomate em fogo baixo\n" +
                "Na mesma panela da almôndega, elimine o excesso de azeite e coloque o molho de tomate, colocando as almôndegas para cozinhar por alguns minutos\n" +
                "Em cerca de 15 minutos as almôndegas estarão totalmente cozidas e o prato estará pronto"));
        receita.add(new Recipes(6, "Lazanha", R.drawable.img2, "Asse bem os peitos de frango já temperados com sal, condimentos do tipo sazón, etc\n" +
                "Acrescente algumas ervas finas no cozimento\n" +
                "Reserve a água que sobrar do cozimento\n" +
                "Tire o osso do peito, o restante você pode optar por desfiar na mão ou bater na batedeira que desfia bem mais rápido\n" +
                "Pegue os temperos o tomate, o pimentão e a cebola\n" +
                "Corte em pedaços bem pequenos\n" +
                "Coloque junto ao frango já desfiado na panela com a água reservada\n" +
                "Ferva até que os temperos dissolvam, e fique um cozidinho bem gostoso"));
        receita.add(new Recipes(7, "Bolo Chocolate", R.drawable.img3, "Bata bem todos os ingredientes da massa (menos o fermento) no liquidificador, aproximadamente 2 a 3 minutos\n" +
                "Acrescente o fermento e bata por mais uns 15 segundos\n" +
                "Coloque em uma forma redonda, untada com manteiga e polvilhada com farinha de trigo\n" +
                "Asse por cerca de 40 minutos em forno médio (180º C), preaquecido"));
        receita.add(new Recipes(9, "Panquecas", R.drawable.img4, "Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        receita.add(new Recipes(10, "Almondegas", R.drawable.img1, " Misture a carne com o ovo, a cebola, o sal, um pouco de azeite de oliva (ou óleo) e a pimenta\n" +
                "Agregue a farinha até dar o ponto de enrolar as almôndegas\n" +
                "Faça pequenas bolinhas\n" +
                "Em uma panela com um pouco de azeite, frite as almôndegas selando-as em fogo alto\n" +
                "Retire as almôndegas e reserve\n" +
                "Em outra panela, esquente o molho de tomate em fogo baixo\n" +
                "Na mesma panela da almôndega, elimine o excesso de azeite e coloque o molho de tomate, colocando as almôndegas para cozinhar por alguns minutos\n" +
                "Em cerca de 15 minutos as almôndegas estarão totalmente cozidas e o prato estará pronto"));
        receita.add(new Recipes(11, "Lazanha", R.drawable.img2, "Asse bem os peitos de frango já temperados com sal, condimentos do tipo sazón, etc\n" +
                "Acrescente algumas ervas finas no cozimento\n" +
                "Reserve a água que sobrar do cozimento\n" +
                "Tire o osso do peito, o restante você pode optar por desfiar na mão ou bater na batedeira que desfia bem mais rápido\n" +
                "Pegue os temperos o tomate, o pimentão e a cebola\n" +
                "Corte em pedaços bem pequenos\n" +
                "Coloque junto ao frango já desfiado na panela com a água reservada\n" +
                "Ferva até que os temperos dissolvam, e fique um cozidinho bem gostoso"));
        receita.add(new Recipes(12, "Bolo Chocolate", R.drawable.img3, "Bata bem todos os ingredientes da massa (menos o fermento) no liquidificador, aproximadamente 2 a 3 minutos\n" +
                "Acrescente o fermento e bata por mais uns 15 segundos\n" +
                "Coloque em uma forma redonda, untada com manteiga e polvilhada com farinha de trigo\n" +
                "Asse por cerca de 40 minutos em forno médio (180º C), preaquecido"));
        receita.add(new Recipes(13, "Panquecas", R.drawable.img4, "Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
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