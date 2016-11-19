package com.livrodereceitas.cookfy.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.livrodereceitas.cookfy.Classes.User;
import com.livrodereceitas.cookfy.R;

import java.io.File;

public class PerfilActivity extends AppCompatActivity {
    private static final int CODIGO_CAMERA = 567;
    private Button camera;
    private String caminhoFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        camera = (Button) findViewById(R.id.cameraPerfil);

        Intent intent = getIntent();
        User usuario = (User) intent.getSerializableExtra("usuario");

        TextView nome = (TextView) this.findViewById(R.id.nome);
        TextView username = (TextView) this.findViewById(R.id.username);
        TextView email = (TextView) this.findViewById(R.id.email);

        nome.setText(usuario.getNome());
        username.setText(usuario.getUsername());
        email.setText(usuario.getEmail());

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) +  "/" + System.currentTimeMillis() +".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));

                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_CAMERA) {
                ImageView foto = (ImageView) findViewById(R.id.imagemPerfil);
                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                foto.setScaleType(ImageView.ScaleType.CENTER_CROP);
                foto.setImageBitmap(bitmapReduzido);
            }
        }
    }
}
