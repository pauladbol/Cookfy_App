package com.livrodereceitas.cookfy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.livrodereceitas.cookfy.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentLoga = new Intent(MainActivity.this,LoginNovoActivity.class);
                startActivity(intentLoga);
            }
        });

        Button cadastrar = (Button) findViewById(R.id.cadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentCadastra = new Intent(MainActivity.this,CadastroActivity.class);
                startActivity(intentCadastra);
            }
        });

    }
}
