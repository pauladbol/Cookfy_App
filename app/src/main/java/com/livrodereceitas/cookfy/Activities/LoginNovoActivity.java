package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginNovoActivity extends AppCompatActivity {

    private static final String REGISTER_URL = "https://cookfy.herokuapp.com/login";
    public static final String KEY_USERNAME = "user";
    public static final String KEY_PASSWORD = "hash";
    public static final String KEY_ADAPTER = "adapter";
    public static final String PREFS_NAME = "MyPrefsFile";

    private EditText usuario;
    private EditText senha;

    private Button logar;

    private String id_user;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_novo);

        usuario = (EditText) findViewById(R.id.login_usuario);
        senha = (EditText) findViewById(R.id.login_senha);
        logar = (Button) findViewById(R.id.login_logar);
        Log.i("script", "login ");
        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!validarUsuario(usuario.getText().toString())) {
//                    usuario.setError("Usuario Invalido");
//                    usuario.requestFocus();
//                } else if (!validarSenha(senha.getText().toString())) {
//                    senha.setError("Senha Invalida");
//                    //senha.requestFocus();
//
//                } else {

                    //Toast.makeText(LoginNovoActivity.this, "Bem vindo " + usuario.getText().toString() +"!", Toast.LENGTH_LONG).show();
                    //Intent intentLogar = new Intent(LoginNovoActivity.this, Main2Activity.class);
                    //startActivity(intentLogar);
                    //finish();
                    autenticaUsuario();


//                }

            }
        });
    }

    private void autenticaUsuario(){
        final String adapter = "application";
        final String username = usuario.getText().toString().trim();
        final String password = senha.getText().toString().trim();

        final String passwordHash = hashSHA256(password);

        final String login;

        if (username.contains("@")) {
            login = "?email=";
        } else {
            login = "?username=";
        }

        final String urlLogin = REGISTER_URL + login + username + "&hash=" + passwordHash;
        Log.i("script", "hash login "+passwordHash);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlLogin, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("script", "id_user "+response.getString("id"));
                            id_user = response.getString("id");
                            token = response.getString("token");

                            salvarTokenID(token, id_user);

                            Toast.makeText(LoginNovoActivity.this, "Bem vindo!", Toast.LENGTH_LONG).show();
                            Intent intentLogar = new Intent(LoginNovoActivity.this, DrawerActivity.class);
                            startActivity(intentLogar);
                            finish();

                        } catch (JSONException e) {
                            //e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                            Intent intentLogar = new Intent(LoginNovoActivity.this, LoginNovoActivity.class);
                            startActivity(intentLogar);
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(LoginNovoActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Ocorreu um erro! Tente novamente mais tarde.",Toast.LENGTH_LONG).show();
                    }
               });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

    public boolean validarSenha(String s) {
        String senha = s;
        if(senha.length()<6){
            return false;
        }else
            return true;
    }
    public boolean validarUsuario(String usuario) {
        String usuarioPattern = "^[aA-zZ]{3,}+(([aA-zZ0-9]+)+)?$";
        Pattern pattern = Pattern.compile(usuarioPattern);
        Matcher matcher = pattern.matcher(usuario);
        return matcher.matches();
    }

    public String hashSHA256(String s) {
        try {
            // Create SHA256 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA256");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void salvarTokenID(String token, String id) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", token);
        editor.putString("id", id);

        Log.i("script", "TOKEN "+token);
        Log.i("script", "id "+id);
        editor.apply();
    }

}
