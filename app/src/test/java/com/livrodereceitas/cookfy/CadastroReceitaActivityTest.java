package com.livrodereceitas.cookfy;

import com.livrodereceitas.cookfy.Activities.CadastroReceitaActivity;

import org.junit.Test;

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
}
