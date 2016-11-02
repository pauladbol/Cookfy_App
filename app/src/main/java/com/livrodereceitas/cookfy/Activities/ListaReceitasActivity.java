package com.livrodereceitas.cookfy.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Adapters.GridViewAdapter;
import com.livrodereceitas.cookfy.R;
import com.livrodereceitas.cookfy.Classes.Recipes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.livrodereceitas.cookfy.Classes.Recipes;

//AppCompatActivity
public class ListaReceitasActivity extends AppCompatActivity {

    private static final String REGISTER_URL = "https://cookfy.herokuapp.com/recipes/";

    List<Recipes> listaReceitas = new ArrayList<>();
    private Recipes receitaTeste;

    Recipes receita = new Recipes();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receitas);


        final GridView gridView = (GridView) findViewById(R.id.gridview);

        listaReceitas = PreencheReceitas();
        BaseAdapter baseAdapterReceita = new GridViewAdapter(this, listaReceitas);

        gridView.setAdapter(baseAdapterReceita);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                receita = (Recipes) gridView.getItemAtPosition(position);

                pegaReceita(receita);

            }
        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.list_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
//        navigationView.setNavigationItemSelectedListener(this);
    }

    private void pegaReceita(final Recipes receitaDetalhe){
        final String urlReceita = REGISTER_URL + receitaDetalhe.getId();
        Log.i("script", "1");
        //Map<String,String> params = new HashMap<String, String>();
        //params.put("recId","1");
        Log.i("script", "2");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlReceita, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    receitaDetalhe.setName(response.getString("name"));
                    receitaDetalhe.setDescription(response.getString("description"));
                    receitaDetalhe.setId(response.getString("id"));

                    Log.i("script", receitaDetalhe.getName());
                    Intent intentDetalhe = new Intent(ListaReceitasActivity.this, DetalheActivity.class);
                    intentDetalhe.putExtra("receita", receitaDetalhe);
                    startActivity(intentDetalhe);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(ListaReceitasActivity.this, ListaReceitasActivity.class);
                    startActivity(intentLogar);
                    finish();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListaReceitasActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){


        };
        Log.i("script", "3");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }


    public List<Recipes> PreencheReceitas() {
        listaReceitas.add(new Recipes("1", "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
                "2 colheres (sopa) de azeite de oliva (30 ml)\n" +
                "1 xícara de cebola bem picada (60 g)\n" +
                "1 ovo\n" +
                "150 g de farinha de rosca\n" +
                "sal a gosto\n" +
                "pimenta-do-reino a gosto\n" +
                "395 g de molho de tomate" , " Misture a carne com o ovo, a cebola, o sal, um pouco de azeite de oliva (ou óleo) e a pimenta\n" +
                "Agregue a farinha até dar o ponto de enrolar as almôndegas\n" +
                "Faça pequenas bolinhas\n" +
                "Em uma panela com um pouco de azeite, frite as almôndegas selando-as em fogo alto\n" +
                "Retire as almôndegas e reserve\n" +
                "Em outra panela, esquente o molho de tomate em fogo baixo\n" +
                "Na mesma panela da almôndega, elimine o excesso de azeite e coloque o molho de tomate, colocando as almôndegas para cozinhar por alguns minutos\n" +
                "Em cerca de 15 minutos as almôndegas estarão totalmente cozidas e o prato estará pronto"));
        listaReceitas.add(new Recipes("2", "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
                "1/2 kg de queijo mussarela\n" +
                "800 g de presunto\n" +
                "1 pote de requeijão médio\n" +
                "1 tomate\n" +
                "1 cebola\n" +
                "1 pimentão\n" +
                "2 massas prontas para lasanha de preferência\n" +
                "5 copos de leite\n\n" + "CREME BRANCO:\n" +
                "\n" +
                "5 copos de leite\n" +
                "1 1/2 colher de farinha de trigo\n" +
                "2 caldos knorr sabor frango\n" +
                "2 caixas de creme de leite", "Asse bem os peitos de frango já temperados com sal, condimentos do tipo sazón, etc\n" +
                "Acrescente algumas ervas finas no cozimento\n" +
                "Reserve a água que sobrar do cozimento\n" +
                "Tire o osso do peito, o restante você pode optar por desfiar na mão ou bater na batedeira que desfia bem mais rápido\n" +
                "Pegue os temperos o tomate, o pimentão e a cebola\n" +
                "Corte em pedaços bem pequenos\n" +
                "Coloque junto ao frango já desfiado na panela com a água reservada\n" +
                "Ferva até que os temperos dissolvam, e fique um cozidinho bem gostoso\n\n CREME BRANCO:\n" +
                "\n" +
                "Depois que dissolver a farinha, leve ao fogo, dissolva o caldo knorr sabor frango e acrescente uma pitada de sal\n" +
                "Quando engrossar, tipo um mingau, retire do fogo e acrescente as caixas de creme de leite\n\n" +
                "MONTAGEM:\n" +
                "\n" +
                "Um pouco de creme branco sob uma forma redonda grande ou um pirex retangular grande, uma cama de massa pronta presunto e requeijão\n" +
                "Depois frango, queijo e mais creme, presunto e requeijão e massa pronta, mais requeijão, presunto, frango, creme e queijo\n" +
                "Nessa ordem fica uma delícia e rende bastante"));
        listaReceitas.add(new Recipes("3", "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
                "\n" +
                "1 xícara (chá) de leite morno\n" +
                "3 ovos\n" +
                "4 colheres (sopa) de margarina derretida\n" +
                "2 xícaras (chá) de açúcar\n" +
                "1 xícara (chá) de chocolate em pó\n" +
                "2 xícaras (chá) de farinha de trigo\n" +
                "1 colher (sopa) de fermento químico em pó\n" +
                "COBERTURA:\n" +
                "\n" +
                "1 xícara (chá) de açúcar\n" +
                "3 colheres (sopa) de amido de milho\n" +
                "5 colheres (sopa) de chocolate em pó\n" +
                "1 xícara (chá) de água\n" +
                "3 colheres (sopa) de margarina ou manteiga\n" +
                "1 colher (chá) de essência de baunilha","Bata bem todos os ingredientes da massa (menos o fermento) no liquidificador, aproximadamente 2 a 3 minutos\n" +
                "Acrescente o fermento e bata por mais uns 15 segundos\nCOBERTURA:\n" +
                "\n" +
                "Leve todos os ingredientes ao fogo até engrossar, em ponto de brigadeiro\n" +
                "Cubra o bolo em seguida\n" +
                "Coloque em uma forma redonda, untada com manteiga e polvilhada com farinha de trigo\n" +
                "Asse por cerca de 40 minutos em forno médio (180º C), preaquecido."));
        listaReceitas.add(new Recipes("4", "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        listaReceitas.add(new Recipes("5", "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
                "2 colheres (sopa) de azeite de oliva (30 ml)\n" +
                "1 xícara de cebola bem picada (60 g)\n" +
                "1 ovo\n" +
                "150 g de farinha de rosca\n" +
                "sal a gosto\n" +
                "pimenta-do-reino a gosto\n" +
                "395 g de molho de tomate" , " Misture a carne com o ovo, a cebola, o sal, um pouco de azeite de oliva (ou óleo) e a pimenta\n" +
                "Agregue a farinha até dar o ponto de enrolar as almôndegas\n" +
                "Faça pequenas bolinhas\n" +
                "Em uma panela com um pouco de azeite, frite as almôndegas selando-as em fogo alto\n" +
                "Retire as almôndegas e reserve\n" +
                "Em outra panela, esquente o molho de tomate em fogo baixo\n" +
                "Na mesma panela da almôndega, elimine o excesso de azeite e coloque o molho de tomate, colocando as almôndegas para cozinhar por alguns minutos\n" +
                "Em cerca de 15 minutos as almôndegas estarão totalmente cozidas e o prato estará pronto"));
        listaReceitas.add(new Recipes("6", "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
                "1/2 kg de queijo mussarela\n" +
                "800 g de presunto\n" +
                "1 pote de requeijão médio\n" +
                "1 tomate\n" +
                "1 cebola\n" +
                "1 pimentão\n" +
                "2 massas prontas para lasanha de preferência\n" +
                "5 copos de leite\n\n" + "CREME BRANCO:\n" +
                "\n" +
                "5 copos de leite\n" +
                "1 1/2 colher de farinha de trigo\n" +
                "2 caldos knorr sabor frango\n" +
                "2 caixas de creme de leite", "Asse bem os peitos de frango já temperados com sal, condimentos do tipo sazón, etc\n" +
                "Acrescente algumas ervas finas no cozimento\n" +
                "Reserve a água que sobrar do cozimento\n" +
                "Tire o osso do peito, o restante você pode optar por desfiar na mão ou bater na batedeira que desfia bem mais rápido\n" +
                "Pegue os temperos o tomate, o pimentão e a cebola\n" +
                "Corte em pedaços bem pequenos\n" +
                "Coloque junto ao frango já desfiado na panela com a água reservada\n" +
                "Ferva até que os temperos dissolvam, e fique um cozidinho bem gostoso\n\n CREME BRANCO:\n" +
                "\n" +
                "Depois que dissolver a farinha, leve ao fogo, dissolva o caldo knorr sabor frango e acrescente uma pitada de sal\n" +
                "Quando engrossar, tipo um mingau, retire do fogo e acrescente as caixas de creme de leite\n\n" +
                "MONTAGEM:\n" +
                "\n" +
                "Um pouco de creme branco sob uma forma redonda grande ou um pirex retangular grande, uma cama de massa pronta presunto e requeijão\n" +
                "Depois frango, queijo e mais creme, presunto e requeijão e massa pronta, mais requeijão, presunto, frango, creme e queijo\n" +
                "Nessa ordem fica uma delícia e rende bastante"));
        listaReceitas.add(new Recipes("7", "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
                "\n" +
                "1 xícara (chá) de leite morno\n" +
                "3 ovos\n" +
                "4 colheres (sopa) de margarina derretida\n" +
                "2 xícaras (chá) de açúcar\n" +
                "1 xícara (chá) de chocolate em pó\n" +
                "2 xícaras (chá) de farinha de trigo\n" +
                "1 colher (sopa) de fermento químico em pó\n" +
                "COBERTURA:\n" +
                "\n" +
                "1 xícara (chá) de açúcar\n" +
                "3 colheres (sopa) de amido de milho\n" +
                "5 colheres (sopa) de chocolate em pó\n" +
                "1 xícara (chá) de água\n" +
                "3 colheres (sopa) de margarina ou manteiga\n" +
                "1 colher (chá) de essência de baunilha","Bata bem todos os ingredientes da massa (menos o fermento) no liquidificador, aproximadamente 2 a 3 minutos\n" +
                "Acrescente o fermento e bata por mais uns 15 segundos\nCOBERTURA:\n" +
                "\n" +
                "Leve todos os ingredientes ao fogo até engrossar, em ponto de brigadeiro\n" +
                "Cubra o bolo em seguida\n" +
                "Coloque em uma forma redonda, untada com manteiga e polvilhada com farinha de trigo\n" +
                "Asse por cerca de 40 minutos em forno médio (180º C), preaquecido."));
        listaReceitas.add(new Recipes("8", "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        listaReceitas.add(new Recipes("9", "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
                "2 colheres (sopa) de azeite de oliva (30 ml)\n" +
                "1 xícara de cebola bem picada (60 g)\n" +
                "1 ovo\n" +
                "150 g de farinha de rosca\n" +
                "sal a gosto\n" +
                "pimenta-do-reino a gosto\n" +
                "395 g de molho de tomate" , " Misture a carne com o ovo, a cebola, o sal, um pouco de azeite de oliva (ou óleo) e a pimenta\n" +
                "Agregue a farinha até dar o ponto de enrolar as almôndegas\n" +
                "Faça pequenas bolinhas\n" +
                "Em uma panela com um pouco de azeite, frite as almôndegas selando-as em fogo alto\n" +
                "Retire as almôndegas e reserve\n" +
                "Em outra panela, esquente o molho de tomate em fogo baixo\n" +
                "Na mesma panela da almôndega, elimine o excesso de azeite e coloque o molho de tomate, colocando as almôndegas para cozinhar por alguns minutos\n" +
                "Em cerca de 15 minutos as almôndegas estarão totalmente cozidas e o prato estará pronto"));
        listaReceitas.add(new Recipes("10", "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
                "1/2 kg de queijo mussarela\n" +
                "800 g de presunto\n" +
                "1 pote de requeijão médio\n" +
                "1 tomate\n" +
                "1 cebola\n" +
                "1 pimentão\n" +
                "2 massas prontas para lasanha de preferência\n" +
                "5 copos de leite\n\n" + "CREME BRANCO:\n" +
                "\n" +
                "5 copos de leite\n" +
                "1 1/2 colher de farinha de trigo\n" +
                "2 caldos knorr sabor frango\n" +
                "2 caixas de creme de leite", "Asse bem os peitos de frango já temperados com sal, condimentos do tipo sazón, etc\n" +
                "Acrescente algumas ervas finas no cozimento\n" +
                "Reserve a água que sobrar do cozimento\n" +
                "Tire o osso do peito, o restante você pode optar por desfiar na mão ou bater na batedeira que desfia bem mais rápido\n" +
                "Pegue os temperos o tomate, o pimentão e a cebola\n" +
                "Corte em pedaços bem pequenos\n" +
                "Coloque junto ao frango já desfiado na panela com a água reservada\n" +
                "Ferva até que os temperos dissolvam, e fique um cozidinho bem gostoso\n\n CREME BRANCO:\n" +
                "\n" +
                "Depois que dissolver a farinha, leve ao fogo, dissolva o caldo knorr sabor frango e acrescente uma pitada de sal\n" +
                "Quando engrossar, tipo um mingau, retire do fogo e acrescente as caixas de creme de leite\n\n" +
                "MONTAGEM:\n" +
                "\n" +
                "Um pouco de creme branco sob uma forma redonda grande ou um pirex retangular grande, uma cama de massa pronta presunto e requeijão\n" +
                "Depois frango, queijo e mais creme, presunto e requeijão e massa pronta, mais requeijão, presunto, frango, creme e queijo\n" +
                "Nessa ordem fica uma delícia e rende bastante"));
        listaReceitas.add(new Recipes("11", "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
                "\n" +
                "1 xícara (chá) de leite morno\n" +
                "3 ovos\n" +
                "4 colheres (sopa) de margarina derretida\n" +
                "2 xícaras (chá) de açúcar\n" +
                "1 xícara (chá) de chocolate em pó\n" +
                "2 xícaras (chá) de farinha de trigo\n" +
                "1 colher (sopa) de fermento químico em pó\n" +
                "COBERTURA:\n" +
                "\n" +
                "1 xícara (chá) de açúcar\n" +
                "3 colheres (sopa) de amido de milho\n" +
                "5 colheres (sopa) de chocolate em pó\n" +
                "1 xícara (chá) de água\n" +
                "3 colheres (sopa) de margarina ou manteiga\n" +
                "1 colher (chá) de essência de baunilha","Bata bem todos os ingredientes da massa (menos o fermento) no liquidificador, aproximadamente 2 a 3 minutos\n" +
                "Acrescente o fermento e bata por mais uns 15 segundos\nCOBERTURA:\n" +
                "\n" +
                "Leve todos os ingredientes ao fogo até engrossar, em ponto de brigadeiro\n" +
                "Cubra o bolo em seguida\n" +
                "Coloque em uma forma redonda, untada com manteiga e polvilhada com farinha de trigo\n" +
                "Asse por cerca de 40 minutos em forno médio (180º C), preaquecido."));
        listaReceitas.add(new Recipes("12", "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        listaReceitas.add(new Recipes("13", "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
                "2 colheres (sopa) de azeite de oliva (30 ml)\n" +
                "1 xícara de cebola bem picada (60 g)\n" +
                "1 ovo\n" +
                "150 g de farinha de rosca\n" +
                "sal a gosto\n" +
                "pimenta-do-reino a gosto\n" +
                "395 g de molho de tomate" , " Misture a carne com o ovo, a cebola, o sal, um pouco de azeite de oliva (ou óleo) e a pimenta\n" +
                "Agregue a farinha até dar o ponto de enrolar as almôndegas\n" +
                "Faça pequenas bolinhas\n" +
                "Em uma panela com um pouco de azeite, frite as almôndegas selando-as em fogo alto\n" +
                "Retire as almôndegas e reserve\n" +
                "Em outra panela, esquente o molho de tomate em fogo baixo\n" +
                "Na mesma panela da almôndega, elimine o excesso de azeite e coloque o molho de tomate, colocando as almôndegas para cozinhar por alguns minutos\n" +
                "Em cerca de 15 minutos as almôndegas estarão totalmente cozidas e o prato estará pronto"));
        listaReceitas.add(new Recipes("14", "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
                "1/2 kg de queijo mussarela\n" +
                "800 g de presunto\n" +
                "1 pote de requeijão médio\n" +
                "1 tomate\n" +
                "1 cebola\n" +
                "1 pimentão\n" +
                "2 massas prontas para lasanha de preferência\n" +
                "5 copos de leite\n\n" + "CREME BRANCO:\n" +
                "\n" +
                "5 copos de leite\n" +
                "1 1/2 colher de farinha de trigo\n" +
                "2 caldos knorr sabor frango\n" +
                "2 caixas de creme de leite", "Asse bem os peitos de frango já temperados com sal, condimentos do tipo sazón, etc\n" +
                "Acrescente algumas ervas finas no cozimento\n" +
                "Reserve a água que sobrar do cozimento\n" +
                "Tire o osso do peito, o restante você pode optar por desfiar na mão ou bater na batedeira que desfia bem mais rápido\n" +
                "Pegue os temperos o tomate, o pimentão e a cebola\n" +
                "Corte em pedaços bem pequenos\n" +
                "Coloque junto ao frango já desfiado na panela com a água reservada\n" +
                "Ferva até que os temperos dissolvam, e fique um cozidinho bem gostoso\n\n CREME BRANCO:\n" +
                "\n" +
                "Depois que dissolver a farinha, leve ao fogo, dissolva o caldo knorr sabor frango e acrescente uma pitada de sal\n" +
                "Quando engrossar, tipo um mingau, retire do fogo e acrescente as caixas de creme de leite\n\n" +
                "MONTAGEM:\n" +
                "\n" +
                "Um pouco de creme branco sob uma forma redonda grande ou um pirex retangular grande, uma cama de massa pronta presunto e requeijão\n" +
                "Depois frango, queijo e mais creme, presunto e requeijão e massa pronta, mais requeijão, presunto, frango, creme e queijo\n" +
                "Nessa ordem fica uma delícia e rende bastante"));
        listaReceitas.add(new Recipes("15", "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
                "\n" +
                "1 xícara (chá) de leite morno\n" +
                "3 ovos\n" +
                "4 colheres (sopa) de margarina derretida\n" +
                "2 xícaras (chá) de açúcar\n" +
                "1 xícara (chá) de chocolate em pó\n" +
                "2 xícaras (chá) de farinha de trigo\n" +
                "1 colher (sopa) de fermento químico em pó\n" +
                "COBERTURA:\n" +
                "\n" +
                "1 xícara (chá) de açúcar\n" +
                "3 colheres (sopa) de amido de milho\n" +
                "5 colheres (sopa) de chocolate em pó\n" +
                "1 xícara (chá) de água\n" +
                "3 colheres (sopa) de margarina ou manteiga\n" +
                "1 colher (chá) de essência de baunilha","Bata bem todos os ingredientes da massa (menos o fermento) no liquidificador, aproximadamente 2 a 3 minutos\n" +
                "Acrescente o fermento e bata por mais uns 15 segundos\nCOBERTURA:\n" +
                "\n" +
                "Leve todos os ingredientes ao fogo até engrossar, em ponto de brigadeiro\n" +
                "Cubra o bolo em seguida\n" +
                "Coloque em uma forma redonda, untada com manteiga e polvilhada com farinha de trigo\n" +
                "Asse por cerca de 40 minutos em forno médio (180º C), preaquecido."));
        listaReceitas.add(new Recipes("16", "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        listaReceitas.add(new Recipes("17", "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
                "2 colheres (sopa) de azeite de oliva (30 ml)\n" +
                "1 xícara de cebola bem picada (60 g)\n" +
                "1 ovo\n" +
                "150 g de farinha de rosca\n" +
                "sal a gosto\n" +
                "pimenta-do-reino a gosto\n" +
                "395 g de molho de tomate" , " Misture a carne com o ovo, a cebola, o sal, um pouco de azeite de oliva (ou óleo) e a pimenta\n" +
                "Agregue a farinha até dar o ponto de enrolar as almôndegas\n" +
                "Faça pequenas bolinhas\n" +
                "Em uma panela com um pouco de azeite, frite as almôndegas selando-as em fogo alto\n" +
                "Retire as almôndegas e reserve\n" +
                "Em outra panela, esquente o molho de tomate em fogo baixo\n" +
                "Na mesma panela da almôndega, elimine o excesso de azeite e coloque o molho de tomate, colocando as almôndegas para cozinhar por alguns minutos\n" +
                "Em cerca de 15 minutos as almôndegas estarão totalmente cozidas e o prato estará pronto"));
        listaReceitas.add(new Recipes("18", "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
                "1/2 kg de queijo mussarela\n" +
                "800 g de presunto\n" +
                "1 pote de requeijão médio\n" +
                "1 tomate\n" +
                "1 cebola\n" +
                "1 pimentão\n" +
                "2 massas prontas para lasanha de preferência\n" +
                "5 copos de leite\n\n" + "CREME BRANCO:\n" +
                "\n" +
                "5 copos de leite\n" +
                "1 1/2 colher de farinha de trigo\n" +
                "2 caldos knorr sabor frango\n" +
                "2 caixas de creme de leite", "Asse bem os peitos de frango já temperados com sal, condimentos do tipo sazón, etc\n" +
                "Acrescente algumas ervas finas no cozimento\n" +
                "Reserve a água que sobrar do cozimento\n" +
                "Tire o osso do peito, o restante você pode optar por desfiar na mão ou bater na batedeira que desfia bem mais rápido\n" +
                "Pegue os temperos o tomate, o pimentão e a cebola\n" +
                "Corte em pedaços bem pequenos\n" +
                "Coloque junto ao frango já desfiado na panela com a água reservada\n" +
                "Ferva até que os temperos dissolvam, e fique um cozidinho bem gostoso\n\n CREME BRANCO:\n" +
                "\n" +
                "Depois que dissolver a farinha, leve ao fogo, dissolva o caldo knorr sabor frango e acrescente uma pitada de sal\n" +
                "Quando engrossar, tipo um mingau, retire do fogo e acrescente as caixas de creme de leite\n\n" +
                "MONTAGEM:\n" +
                "\n" +
                "Um pouco de creme branco sob uma forma redonda grande ou um pirex retangular grande, uma cama de massa pronta presunto e requeijão\n" +
                "Depois frango, queijo e mais creme, presunto e requeijão e massa pronta, mais requeijão, presunto, frango, creme e queijo\n" +
                "Nessa ordem fica uma delícia e rende bastante"));
        listaReceitas.add(new Recipes("19", "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
                "\n" +
                "1 xícara (chá) de leite morno\n" +
                "3 ovos\n" +
                "4 colheres (sopa) de margarina derretida\n" +
                "2 xícaras (chá) de açúcar\n" +
                "1 xícara (chá) de chocolate em pó\n" +
                "2 xícaras (chá) de farinha de trigo\n" +
                "1 colher (sopa) de fermento químico em pó\n" +
                "COBERTURA:\n" +
                "\n" +
                "1 xícara (chá) de açúcar\n" +
                "3 colheres (sopa) de amido de milho\n" +
                "5 colheres (sopa) de chocolate em pó\n" +
                "1 xícara (chá) de água\n" +
                "3 colheres (sopa) de margarina ou manteiga\n" +
                "1 colher (chá) de essência de baunilha","Bata bem todos os ingredientes da massa (menos o fermento) no liquidificador, aproximadamente 2 a 3 minutos\n" +
                "Acrescente o fermento e bata por mais uns 15 segundos\nCOBERTURA:\n" +
                "\n" +
                "Leve todos os ingredientes ao fogo até engrossar, em ponto de brigadeiro\n" +
                "Cubra o bolo em seguida\n" +
                "Coloque em uma forma redonda, untada com manteiga e polvilhada com farinha de trigo\n" +
                "Asse por cerca de 40 minutos em forno médio (180º C), preaquecido\n INFORMAÇÕES ADICIONAIS\n"  +
                "Filme de referência: Matilda."));
        listaReceitas.add(new Recipes("20", "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        listaReceitas.add(new Recipes("21", "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
                "2 colheres (sopa) de azeite de oliva (30 ml)\n" +
                "1 xícara de cebola bem picada (60 g)\n" +
                "1 ovo\n" +
                "150 g de farinha de rosca\n" +
                "sal a gosto\n" +
                "pimenta-do-reino a gosto\n" +
                "395 g de molho de tomate" , " Misture a carne com o ovo, a cebola, o sal, um pouco de azeite de oliva (ou óleo) e a pimenta\n" +
                "Agregue a farinha até dar o ponto de enrolar as almôndegas\n" +
                "Faça pequenas bolinhas\n" +
                "Em uma panela com um pouco de azeite, frite as almôndegas selando-as em fogo alto\n" +
                "Retire as almôndegas e reserve\n" +
                "Em outra panela, esquente o molho de tomate em fogo baixo\n" +
                "Na mesma panela da almôndega, elimine o excesso de azeite e coloque o molho de tomate, colocando as almôndegas para cozinhar por alguns minutos\n" +
                "Em cerca de 15 minutos as almôndegas estarão totalmente cozidas e o prato estará pronto"));
        listaReceitas.add(new Recipes("22", "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
                "1/2 kg de queijo mussarela\n" +
                "800 g de presunto\n" +
                "1 pote de requeijão médio\n" +
                "1 tomate\n" +
                "1 cebola\n" +
                "1 pimentão\n" +
                "2 massas prontas para lasanha de preferência\n" +
                "5 copos de leite\n\n" + "CREME BRANCO:\n" +
                "\n" +
                "5 copos de leite\n" +
                "1 1/2 colher de farinha de trigo\n" +
                "2 caldos knorr sabor frango\n" +
                "2 caixas de creme de leite", "Asse bem os peitos de frango já temperados com sal, condimentos do tipo sazón, etc\n" +
                "Acrescente algumas ervas finas no cozimento\n" +
                "Reserve a água que sobrar do cozimento\n" +
                "Tire o osso do peito, o restante você pode optar por desfiar na mão ou bater na batedeira que desfia bem mais rápido\n" +
                "Pegue os temperos o tomate, o pimentão e a cebola\n" +
                "Corte em pedaços bem pequenos\n" +
                "Coloque junto ao frango já desfiado na panela com a água reservada\n" +
                "Ferva até que os temperos dissolvam, e fique um cozidinho bem gostoso\n\n CREME BRANCO:\n" +
                "\n" +
                "Depois que dissolver a farinha, leve ao fogo, dissolva o caldo knorr sabor frango e acrescente uma pitada de sal\n" +
                "Quando engrossar, tipo um mingau, retire do fogo e acrescente as caixas de creme de leite\n\n" +
                "MONTAGEM:\n" +
                "\n" +
                "Um pouco de creme branco sob uma forma redonda grande ou um pirex retangular grande, uma cama de massa pronta presunto e requeijão\n" +
                "Depois frango, queijo e mais creme, presunto e requeijão e massa pronta, mais requeijão, presunto, frango, creme e queijo\n" +
                "Nessa ordem fica uma delícia e rende bastante"));
        listaReceitas.add(new Recipes("23", "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
                "\n" +
                "1 xícara (chá) de leite morno\n" +
                "3 ovos\n" +
                "4 colheres (sopa) de margarina derretida\n" +
                "2 xícaras (chá) de açúcar\n" +
                "1 xícara (chá) de chocolate em pó\n" +
                "2 xícaras (chá) de farinha de trigo\n" +
                "1 colher (sopa) de fermento químico em pó\n" +
                "COBERTURA:\n" +
                "\n" +
                "1 xícara (chá) de açúcar\n" +
                "3 colheres (sopa) de amido de milho\n" +
                "5 colheres (sopa) de chocolate em pó\n" +
                "1 xícara (chá) de água\n" +
                "3 colheres (sopa) de margarina ou manteiga\n" +
                "1 colher (chá) de essência de baunilha","Bata bem todos os ingredientes da massa (menos o fermento) no liquidificador, aproximadamente 2 a 3 minutos\n" +
                "Acrescente o fermento e bata por mais uns 15 segundos\nCOBERTURA:\n" +
                "\n" +
                "Leve todos os ingredientes ao fogo até engrossar, em ponto de brigadeiro\n" +
                "Cubra o bolo em seguida\n" +
                "Coloque em uma forma redonda, untada com manteiga e polvilhada com farinha de trigo\n" +
                "Asse por cerca de 40 minutos em forno médio (180º C), preaquecido\nINFORMAÇÕES ADICIONAIS\n"  +
                "Filme de referência: Matilda."));
        listaReceitas.add(new Recipes("24", "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        return listaReceitas;
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.drawer, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//
//        if (id == R.id.nav_perfil) {
//            Intent intentPerfil = new Intent(ListaReceitasActivity.this, PerfilActivity.class);
//            startActivity(intentPerfil);
//        } else if (id == R.id.nav_favoritos) {
//            reqReceitas("favoritos");
//
//        } else if (id == R.id.nav_config) {
//            Intent intentConfig = new Intent(ListaReceitasActivity.this, ListaReceitasActivity.class);
//            startActivity(intentConfig);
//
//        } else if (id == R.id.nav_sair) {
//            usuarioLogout();
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//
//
//
//        return true;
//    }
//
//
//    private void reqReceitas(String type){
//        if (type.equals("favoritos")) {
//            urlReq = REGISTER_URL_FAV;
//        } else {
//            urlReq = REGISTER_URL_CATEG;
//        }
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
//                urlReq, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    id = response.getString("id");
//                    name = response.getString("name");
//                    description = response.getString("description");
//                    executionTime = response.getString("executionTime");
//                    difficulty = response.getString("difficulty");
//                    ingredientes = response.getString("ingredientes");
//                    recipeBooks = response.getString("recipeBooks");
//
//                    Toast.makeText(ListaReceitasActivity.this, "!", Toast.LENGTH_LONG).show();
//                    Intent intentLogar = new Intent(ListaReceitasActivity.this, ListaReceitasActivity.class);
//                    startActivity(intentLogar);
//                    finish();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
//                    Intent intentLogar = new Intent(ListaReceitasActivity.this, DrawerActivity.class);
//                    startActivity(intentLogar);
//                    finish();
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ListaReceitasActivity.this,error.toString(),Toast.LENGTH_LONG).show();
//                    }
//                }){
//
//
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonObjReq);
//    }
//
//    private void usuarioLogout() {
//
//        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//        SharedPreferences.Editor editor = settings.edit();
//
//        editor.remove("token");
//        editor.remove("id");
//
//        editor.commit();
//
//        Intent intentSair = new Intent(ListaReceitasActivity.this, MainActivity.class);
//        startActivity(intentSair);
//    }

}
