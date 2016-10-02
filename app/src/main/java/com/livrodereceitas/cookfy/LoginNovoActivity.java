package com.livrodereceitas.cookfy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginNovoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_novo);

        final EditText usuario = (EditText) findViewById(R.id.login_usuario);
        final EditText senha = (EditText) findViewById(R.id.login_senha);
        Button logar = (Button) findViewById(R.id.login_logar);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validarUsuario(usuario.getText().toString())) {
                    usuario.setError("Usuario Invalido");
                    usuario.requestFocus();
                }else if (!validarSenha(senha.getText().toString())){
                    senha.setError("Senha Invalido");
                    senha.requestFocus();
                }else {
                    Toast.makeText(LoginNovoActivity.this, "Bem vindo", Toast.LENGTH_LONG).show();
                    Intent intentLogar = new Intent(LoginNovoActivity.this, CadastrarReceitaActivity.class);
                    startActivity(intentLogar);
                    finish();
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
    private boolean validarUsuario(String usuario) {
        String usuarioPattern = "^[aA-zZ]{3,}+(([aA-zZ0-9]+)+)?$";
        Pattern pattern = Pattern.compile(usuarioPattern);
        Matcher matcher = pattern.matcher(usuario);
        return matcher.matches();
    }
}
