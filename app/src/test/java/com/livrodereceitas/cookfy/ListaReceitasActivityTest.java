package com.livrodereceitas.cookfy;

import android.content.Intent;
import android.os.Parcelable;

import com.livrodereceitas.cookfy.Activities.ListaReceitasActivity;
import com.livrodereceitas.cookfy.Classes.Recipes;

import org.json.JSONArray;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class ListaReceitasActivityTest {
    ListaReceitasActivity lista = new ListaReceitasActivity();

    Recipes recipe1 = Mockito.mock(Recipes.class);
    Recipes recipe2 = Mockito.mock(Recipes.class);

    ArrayList<Recipes> recipesList = new ArrayList<Recipes>();

    @Test
    public void montaListaIngredientesTest() {

        JSONArray jsonArray = Mockito.mock(JSONArray.class);

        assertEquals(lista.montalistaIngredientes(jsonArray),Mockito.anyIterable());
    }

    @Ignore
    public void preencheReceitasTest() {
        recipesList.add(recipe1);
        recipesList.add(recipe2);

        Mockito.when(lista.preencheReceitas()).thenReturn(recipesList);

        Intent intent = Mockito.mock(Intent.class);

        assertEquals(lista.preencheReceitas(),recipesList);

        Mockito.verify(lista.preencheReceitas(), Mockito.times(1));
        //assertEquals(lista.preencheReceitas(),recipesList);
    }
}
