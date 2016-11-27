package com.livrodereceitas.cookfy;

import com.livrodereceitas.cookfy.Activities.PesquisaIngredienteActivity;
import com.livrodereceitas.cookfy.Classes.Recipes;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class PesquisaIngredienteActivityTest {

    PesquisaIngredienteActivity pesquisa = new PesquisaIngredienteActivity();

    @Test
    public void testIngredientTrue() {
        assertTrue(pesquisa.validarIngrediente("teste"));
    }

    @Test
    public void testIngredientFalse() {
        assertFalse(pesquisa.validarIngrediente("teste2"));
    }

    @Test
    public void montaReceitasPesquisaTest() {

        JSONArray jsonArray = Mockito.mock(JSONArray.class);

        assertEquals(pesquisa.montaReceitasPesquisa(jsonArray),Mockito.anyIterable());

    }
}
