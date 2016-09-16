package com.livrodereceitas.cookfy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final EditText email = (EditText) findViewById(R.id.cadastro_email);
        final EditText senha = (EditText) findViewById(R.id.cadastro_senha);
        final EditText usuario = (EditText) findViewById(R.id.cadastro_usuario);
        final EditText nome = (EditText) findViewById(R.id.cadastro_nome);
        Button validar = (Button) findViewById(R.id.cadastro_cadastrar);

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validarEmail(email.getText().toString())) {
                    email.setError("Email Invalido");
                    email.requestFocus();
                } else if (!validarNome(nome.getText().toString())) {
                    nome.setError("Nome Invalido");
                    nome.requestFocus();
                } else if (!validarUsuario(usuario.getText().toString())) {
                    usuario.setError("Usuario Invalido");
                    usuario.requestFocus();
                }else if (!validarSenha(senha.getText().toString())){
                    senha.setError("Senha Invalido");
                    senha.requestFocus();
                } else {
                    Toast.makeText(CadastroActivity.this, "sucesso", Toast.LENGTH_LONG).show();
                    Intent teste = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(teste);
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


}
