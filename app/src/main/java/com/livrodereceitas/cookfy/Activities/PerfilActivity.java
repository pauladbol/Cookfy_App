package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.livrodereceitas.cookfy.Classes.User;
import com.livrodereceitas.cookfy.R;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Intent intent = getIntent();
        User usuario = (User) intent.getSerializableExtra("usuario");

        TextView nome = (TextView) this.findViewById(R.id.nome);
        TextView username = (TextView) this.findViewById(R.id.username);
        TextView email = (TextView) this.findViewById(R.id.email);

        nome.setText(usuario.getNome());
        username.setText(usuario.getUsername());
        email.setText(usuario.getEmail());

    }
}
