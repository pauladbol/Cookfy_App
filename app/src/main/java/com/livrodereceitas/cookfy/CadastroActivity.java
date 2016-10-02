package com.livrodereceitas.cookfy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroActivity extends AppCompatActivity {
    private static final String REGISTER_URL = "http://simplifiedcoding.16mb.com/UserRegistration/volleyRegister.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ADAPTER = "adapter";
    public static final String PREFS_NAME = "MyPrefsFile";

    private EditText usuario;
    private EditText senha;
    private EditText email;
    private EditText nome;

    private Button validar;

    private String id_user;
    private String token;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        email = (EditText) findViewById(R.id.cadastro_email);
        senha = (EditText) findViewById(R.id.cadastro_senha);
        usuario = (EditText) findViewById(R.id.cadastro_usuario);
        nome = (EditText) findViewById(R.id.cadastro_nome);
        validar = (Button) findViewById(R.id.cadastro_cadastrar);

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validarNome(nome.getText().toString())) {
                    nome.setError("Nome Invalido");
                    nome.requestFocus();
                }else if (!validarUsuario(usuario.getText().toString())) {
                    usuario.setError("Usuario Invalido");
                    usuario.requestFocus();
                }else if (!validarEmail(email.getText().toString())) {
                        email.setError("Email Invalido");
                        email.requestFocus();
                } else if (!validarSenha(senha.getText().toString())){
                    senha.setError("Senha Invalido");
                    senha.requestFocus();
                } else {
                    registerUser();
                }

            }
        });

    }

    private boolean validarSenha(String s) {
        String senha = s;
        if(senha.length()<6){
            return false;
        }else
            return true;
    }

    private boolean validarEmail(String email) {
        String emailPattern = "[a-zA-Z0-9][a-zA-Z0-9\\._-]+@([a-zA-Z0-9\\._-]+\\.)[a-zA-Z-0-9]{2,3}";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validarNome(String nome) {
        String nomePattern = "^[aA-zZ]{3,}+(([ aA-zZ]+)+)?$";
        Pattern pattern = Pattern.compile(nomePattern);
        Matcher matcher = pattern.matcher(nome);
        return matcher.matches();
    }

    private boolean validarUsuario(String usuario) {
        String usuarioPattern = "^[aA-zZ]{3,}+(([aA-zZ0-9]+)+)?$";
        Pattern pattern = Pattern.compile(usuarioPattern);
        Matcher matcher = pattern.matcher(usuario);
        return matcher.matches();
    }

    private void registerUser(){
        final String adapter = "application";
        final String username = usuario.getText().toString().trim();
        final String password = senha.getText().toString().trim();
        final String emaill = email.getText().toString().trim();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                REGISTER_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    id_user = response.getString("id");
                    token = response.getString("token");

                    salvarTokenID(token, id_user);

                    Toast.makeText(CadastroActivity.this, "Bem vindo!", Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(CadastroActivity.this, Main2Activity.class);
                    startActivity(intentLogar);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(CadastroActivity.this, CadastroActivity.class);
                    startActivity(intentLogar);
                    finish();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,username);
                params.put(KEY_PASSWORD,password);
                params.put(KEY_EMAIL, emaill);
                params.put(KEY_ADAPTER,adapter);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

    public String hashMd5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
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

        editor.commit();
    }

}
