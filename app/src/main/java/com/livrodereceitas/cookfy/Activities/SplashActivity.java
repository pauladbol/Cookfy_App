package com.livrodereceitas.cookfy.Activities;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pauladbol on 2016-09-29.
 */
public class SplashActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";

    // Timer da splash screen
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                verificarUsuario();

                final boolean token = verificarUsuario();

                if (token) {
                    Intent i = new Intent(SplashActivity.this, DrawerActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal


                // Fecha esta activity
                finish();
            }


        }, SPLASH_TIME_OUT);
    }

    private boolean verificarUsuario(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.contains("token")) {
            return true;
        } else {
            return false;
        }
    }
}