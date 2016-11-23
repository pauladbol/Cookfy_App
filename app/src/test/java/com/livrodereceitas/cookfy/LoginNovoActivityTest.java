package com.livrodereceitas.cookfy;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.livrodereceitas.cookfy.Activities.LoginNovoActivity;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class LoginNovoActivityTest {

    //@Mock
    MessageDigest digest = Mockito.mock(MessageDigest.class);;

    //@Mock
    SharedPreferences sharedPreferences = Mockito.mock(SharedPreferences.class);;

    //@Mock
    ContextWrapper context = Mockito.mock(ContextWrapper.class);;

    //@Mock
    SharedPreferences.Editor editor = Mockito.mock(SharedPreferences.Editor.class);;

    LoginNovoActivity login = new LoginNovoActivity();

    @Test
    public void testValidaSenhaTrue() {
        assertTrue(login.validarSenha("valida"));
    }

    @Test
    public void testValidaSenhaFalse() {
        assertFalse(login.validarSenha("nao"));
    }

    @Test
    public void testUsuarioTrue() {
        assertTrue(login.validarUsuario("teste"));
    }

    @Test
    public void testUsuarioFalse() {
        assertFalse(login.validarUsuario("teste teste"));
    }

    @Test /*(expected = NoSuchAlgorithmException.class)*/
    public void testHash() {
        String hash = login.hashSHA256("teste");
        String senha = "teste";
        assertEquals(hash,login.hashSHA256(senha));

    }

    @Ignore
    public void testSalvarTokenID() {
        Mockito.when(context.getSharedPreferences(Mockito.anyString(), Mockito.anyInt())).thenReturn(sharedPreferences);
        login.salvarTokenID("teste","id");
        Mockito.verify(editor,Mockito.times(1)).apply();
    }
}
