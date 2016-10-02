package com.livrodereceitas.cookfy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginNovoActivity extends AppCompatActivity {

    private static final String REGISTER_URL = "http://simplifiedcoding.16mb.com/UserRegistration/volleyRegister.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    private EditText usuario;
    private EditText senha;

    private Button logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_novo);

        usuario = (EditText) findViewById(R.id.login_usuario);
        senha = (EditText) findViewById(R.id.login_senha);
        logar = (Button) findViewById(R.id.login_logar);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validarUsuario(usuario.getText().toString())) {
                    usuario.setError("Usuario Invalido");
                    usuario.requestFocus();
                } else if (!validarSenha(senha.getText().toString())) {
                    senha.setError("Senha Invalido");
                    senha.requestFocus();
                } else {
                    Toast.makeText(LoginNovoActivity.this, "Bem vindo", Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(LoginNovoActivity.this, CadastrarReceitaActivity.class);
                    startActivity(intentLogar);
                    finish();
                }
                autenticaUsuario();
            }
        });
    }

    private void autenticaUsuario(){
        final String username = usuario.getText().toString().trim();
        final String password = senha.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginNovoActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginNovoActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,username);
                params.put(KEY_PASSWORD,password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    private boolean validarSenha(String s) {
        String senha = s;
        if(senha.length()<6){
            return false;
        }else
            return true;
    }
    private boolean validarUsuario(String usuario) {
        String usuarioPattern = "^[aA-zZ]{3,}+(([aA-zZ0-9]+)+)?$";
        Pattern pattern = Pattern.compile(usuarioPattern);
        Matcher matcher = pattern.matcher(usuario);
        return matcher.matches();
    }

}
