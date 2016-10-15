package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Adapters.ImagemPagerAdapter;
import com.livrodereceitas.cookfy.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    private static final String REGISTER_URL = "http://simplifiedcoding.16mb.com/UserRegistration/volleyRegister.php";
    public static final String KEY_USERNAME = "user";
    public static final String KEY_PASSWORD = "hash";
    public static final String KEY_ADAPTER = "adapter";


    private String id;
    private String name;
    private String description;
    private String executionTime;
    private String difficulty;
    private String ingredientes;
    private String recipeBooks;

    private int[] imagens = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ViewPager g = (ViewPager) findViewById(R.id.viewPager);
        g.setAdapter(new ImagemPagerAdapter(this, imagens));


// somewhere where you setup your viewPager add this
        g.setOnTouchListener(
                new View.OnTouchListener() {
                    private boolean moved;

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            moved = false;
                        }
                        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                            moved = true;
                        }
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            if (!moved) {
                                view.performClick();
                            }
                        }

                        return false;
                    }
                }
        );

        g.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intentLogar = new Intent(Main2Activity.this, ListaReceitasActivity.class);
                        startActivity(intentLogar);
                    }
                }
        );


    }

    private void pegaReceitas(){

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                REGISTER_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    id = response.getString("id");
                    name = response.getString("name");
                    description = response.getString("description");
                    executionTime = response.getString("executionTime");
                    difficulty = response.getString("difficulty");
                    ingredientes = response.getString("ingredientes");
                    recipeBooks = response.getString("recipeBooks");

                    Toast.makeText(Main2Activity.this, "Bem vindo!", Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(Main2Activity.this, ListaReceitasActivity.class);
                    startActivity(intentLogar);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(Main2Activity.this, Main2Activity.class);
                    startActivity(intentLogar);
                    finish();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }
}


