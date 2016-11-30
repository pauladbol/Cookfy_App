package com.livrodereceitas.cookfy;

import com.livrodereceitas.cookfy.Activities.DrawerActivity;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Thiago on 27/11/2016.
 */
public class DrawerActivityTest {
    DrawerActivity drawerActivity = new DrawerActivity();

    @Test
    public void montaUrlFavorityTest(){
        assertTrue(drawerActivity.montaUrl("favoritos","1").contains("FAVORITY"));
    }

    @Test
    public void montaUrlUserTest(){
        assertTrue(drawerActivity.montaUrl("minhas","1").contains("users"));
    }

    @Test
    public void montaUrlRecipeTest(){
        assertTrue(drawerActivity.montaUrl("sdçflksdf","1").contains("recipes"));
    }
}
