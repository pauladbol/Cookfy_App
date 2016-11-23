package com.livrodereceitas.cookfy;

import com.livrodereceitas.cookfy.Activities.PesquisaIngredienteActivity;

import org.junit.Test;

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
}
