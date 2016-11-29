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
    public void montaUrlTest(){
        assertTrue(drawerActivity.montaUrl("favoritos","1").contains("FAVORITY"));

    }
}
