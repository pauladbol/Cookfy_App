package com.livrodereceitas.cookfy;

import com.livrodereceitas.cookfy.Activities.PesquisaIngredienteActivity;
import com.livrodereceitas.cookfy.Classes.Ingrediente;
import com.livrodereceitas.cookfy.Classes.Recipes;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

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
    public void testMontaReceitasPesquisa() {

        JSONArray jsonArray = Mockito.mock(JSONArray.class);

        assertEquals(pesquisa.montaReceitasPesquisa(jsonArray),Mockito.anyIterable());

    }

    @Test
    public void testMontaListaIngredientes() {
        List<Ingrediente> lista = Mockito.anyList();

        assertEquals(pesquisa.montaUrlIngredientes(lista), Mockito.anyString());

    }
}
