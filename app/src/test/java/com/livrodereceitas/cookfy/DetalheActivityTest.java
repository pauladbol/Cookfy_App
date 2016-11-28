package com.livrodereceitas.cookfy;

import android.test.MoreAsserts;

import com.livrodereceitas.cookfy.Activities.DetalheActivity;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Thiago on 27/11/2016.
 */
public class DetalheActivityTest {
    DetalheActivity detalhe = new DetalheActivity();

    @Test
    public void montaStringIngredientesTest(){
        ArrayList<String> listaIngrediente = Mockito.mock(ArrayList.class);

        assertEquals(detalhe.montaStringIngredientes(listaIngrediente), Mockito.anyString());
    }
    @Test
    public void converteDificuldadeHardTest(){
        assertEquals(detalhe.converteDificuldade("HARD"), "Difícil");
    }
    @Test
    public void converteDificuldadeEasyTest(){
        assertEquals(detalhe.converteDificuldade("EASY"), "Fácil");
    }
    @Test
    public void converteDificuldadeMediumTest(){
        assertEquals(detalhe.converteDificuldade("MEDIUM"), "Média");
    }
}
