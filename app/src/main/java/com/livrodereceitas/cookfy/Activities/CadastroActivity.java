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
import com.google.android.gms.common.api.GoogleApiClient;
import com.livrodereceitas.cookfy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroActivity extends AppCompatActivity {
    public static final String REGISTER_URL = "https://cookfy.herokuapp.com/signup";
    public static final String KEY_NAME = "name";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "hash";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ADAPTER = "adapter";
    public static final String KEY_DATEC = "dateCreated";
    public static final String KEY_DATEU = "dateUpdated";
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
//                    Toast.makeText(CadastroActivity.this, "Cadastro feito com sucesso", Toast.LENGTH_LONG).show();
//                    Intent intentLogar = new Intent(CadastroActivity.this, LoginNovoActivity.class);
//                    startActivity(intentLogar);
//                    finish();
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
        final HashMap<String, String> params = new HashMap<String, String>();

        final JSONObject jsonobj = new JSONObject();

        final String adapter = "application";
        final String name = nome.getText().toString().trim();
        final String username = usuario.getText().toString().trim();
        final String password = senha.getText().toString().trim();
        final String emaill = email.getText().toString().trim();
        final Date date = new Date();

        Log.i("script", "data "+date);
        final String passwordHash = hashSHA256(password);
        Log.i("script", "hash cadastro "+passwordHash);

        try {
            jsonobj.put(KEY_NAME,name);
            jsonobj.put(KEY_USERNAME,username);
            jsonobj.put(KEY_EMAIL, emaill);
            jsonobj.put(KEY_PASSWORD,passwordHash);
            jsonobj.put(KEY_ADAPTER,adapter);
//            jsonobj.put(KEY_DATEC,date);
//            jsonobj.put(KEY_DATEU,date);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"123456789: " + e.getMessage(),Toast.LENGTH_LONG).show();
        }

        Log.i("script", jsonobj.toString());

//        params.put(KEY_NAME,name);
//        params.put(KEY_USERNAME,username);
//        params.put(KEY_EMAIL, emaill);
//        params.put(KEY_PASSWORD,passwordHash);
//        params.put(KEY_ADAPTER,adapter);
//        params.put(KEY_DATEC,date);
//        params.put(KEY_DATEU,date);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                REGISTER_URL, jsonobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //try {
//                    id_user = response.getString("id");
//                    token = response.getString("token");
//
//                    salvarTokenID(token, id_user);
                    Log.i("script", "entrou no request!!");
                    Toast.makeText(CadastroActivity.this, "Bem vindo!", Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(CadastroActivity.this, LoginNovoActivity.class);
                    startActivity(intentLogar);
                    finish();

//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
//                    Intent intentLogar = new Intent(CadastroActivity.this, CadastroActivity.class);
//                    startActivity(intentLogar);
//                    finish();
//                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroActivity.this,"!!"+error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
//            @Override
//            protected Map<String,String> getParams(){
//                params.put(KEY_NAME,name);
//                params.put(KEY_USERNAME,username);
//                params.put(KEY_EMAIL, emaill);
//                params.put(KEY_PASSWORD,passwordHash);
//                params.put(KEY_ADAPTER,adapter);
//                params.put(KEY_DATEC,date);
//                params.put(KEY_DATEU,date);
//                return params;
//            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
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
}
