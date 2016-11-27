package com.livrodereceitas.cookfy;

import com.livrodereceitas.cookfy.Activities.ListaReceitasActivity;

import org.json.JSONArray;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;

public class ListaReceitasActivityTest {
    ListaReceitasActivity lista = new ListaReceitasActivity();

    @Test
    public void montaListaIngredientesTest() {

        JSONArray jsonArray = Mockito.mock(JSONArray.class);

        assertEquals(lista.montalistaIngredientes(jsonArray),Mockito.anyIterable());
    }
}
