package com.livrodereceitas.cookfy;

import com.livrodereceitas.cookfy.Activities.CadastroActivity;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;

/**
 * Created by pauladbol on 2016-11-17.
 */
public class CadastroActivityTest {
    CadastroActivity cadastro = new CadastroActivity();

    @Test
    public void testValidaSenhaTrue() {
        assertTrue(cadastro.validarSenha("valida"));
    }

    @Test
    public void testValidaSenhaFalse() {
        assertFalse(cadastro.validarSenha("nao"));
    }


    @Test
    public void testEmailTrue() {
        assertTrue(cadastro.validarEmail("teste@teste.com"));
    }

    @Test
    public void testEmailFalse() {
        assertFalse(cadastro.validarSenha("teste"));
    }

    @Test
    public void testNomeTrue() {
        assertTrue(cadastro.validarNome("teste"));
    }

    @Test
    public void testNomeFalse() {
        assertFalse(cadastro.validarNome("teste2"));
    }

    @Test
    public void testUsuarioTrue() {
        assertTrue(cadastro.validarUsuario("teste"));
    }

    @Test
    public void testUsuarioFalse() {
        assertFalse(cadastro.validarUsuario("teste teste"));
    }

    @Test
    public void testHash() {

    }


    //metodo void: verify times called
    //Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
}
