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

        receita.add(new Recipes(1, "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
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
        receita.add(new Recipes(2, "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
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
        receita.add(new Recipes(3, "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
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
        receita.add(new Recipes(4, "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        receita.add(new Recipes(5, "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
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
        receita.add(new Recipes(6, "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
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
        receita.add(new Recipes(7, "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
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
        receita.add(new Recipes(8, "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        receita.add(new Recipes(9, "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
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
        receita.add(new Recipes(10, "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
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
        receita.add(new Recipes(11, "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
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
        receita.add(new Recipes(12, "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        receita.add(new Recipes(12, "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
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
        receita.add(new Recipes(14, "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
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
        receita.add(new Recipes(15, "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
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
        receita.add(new Recipes(16, "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        receita.add(new Recipes(17, "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
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
        receita.add(new Recipes(18, "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
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
        receita.add(new Recipes(19, "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
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
        receita.add(new Recipes(20, "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
                "Unte uma frigideira com manteiga, espere esquentar e despeje uma concha de massa\n" +
                "Faça movimentos circulares para que a massa se espalhe por toda a frigideira\n" +
                "Deixe assar até que a borda obtenha uma cor dourada\n" +
                "Espere até a massa se soltar do fundo, vire e deixe fritar do outro lado\n" +
                "Recheie a gosto, enrole e sirva"));
        receita.add(new Recipes(21, "Almondegas", R.drawable.img1,"500 g de carne moída\n" +
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
        receita.add(new Recipes(22, "Lazanha", R.drawable.img2, "2 peitos de frango\n" +
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
        receita.add(new Recipes(23, "Bolo Chocolate", R.drawable.img3, "MASSA:\n" +
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
        receita.add(new Recipes(24, "Panquecas", R.drawable.img4, "1 copo de leite\n" +
                "1 ovo\n" +
                "1 copo de farinha de trigo\n" +
                "1 colher (sopa) de óleo\n" +
                "1 pitada de sal","Bata todos os ingredientes no liquidificador até obter uma consistência cremosa\n" +
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

        picture.setImageResource(receita.getDrawableId());
        name.setText(receita.getName());
        //ingredientes.setText(receita.getIngredientes());

        return v;
    }


}