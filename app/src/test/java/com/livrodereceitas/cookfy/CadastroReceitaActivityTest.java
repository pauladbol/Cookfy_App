package com.livrodereceitas.cookfy;

import com.livrodereceitas.cookfy.Activities.CadastroReceitaActivity;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class CadastroReceitaActivityTest {
    CadastroReceitaActivity cadastro = new CadastroReceitaActivity();
    @Test
    public void testIngredientTrue() {
        assertTrue(cadastro.validarIngrediente("teste"));
    }

    @Test
    public void testIngredientFalse() {
        assertFalse(cadastro.validarIngrediente("teste2"));
    }

    @Test
    public void testDificuldadeDificil() {
        assertEquals(cadastro.deParaDificuldade("Difícil"), "HARD");
    }

    @Test
    public void testDificuldadeMedia() {
        assertEquals(cadastro.deParaDificuldade("Média"), "MEDIUM");
    }

    @Test
    public void testDificuldadeFacil() {
        assertEquals(cadastro.deParaDificuldade("Fácil"), "EASY");
    }
}
