package com.livrodereceitas.cookfy.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.livrodereceitas.cookfy.Classes.User;
import com.livrodereceitas.cookfy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PerfilActivity extends AppCompatActivity {
    public static final String REGISTER_URL = "https://cookfy.herokuapp.com/users/";
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_PICTURE = "picture";
    private static final int CODIGO_CAMERA = 567;
    private Button camera;
    private  Button galeria;
    private String caminhoFoto;
    private  String encodedImage;
    private Button salvarFoto;
    private byte[] imagem;
    private Bitmap testeGaleria;
    private ImageView foto;
    private File arquivoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        camera = (Button) findViewById(R.id.cameraPerfil);
        galeria = (Button)findViewById(R.id.galeria);

        salvarFoto = (Button) findViewById((R.id.salvarFotoPerfil));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        User usuario = (User) intent.getSerializableExtra("usuario");

        TextView nome = (TextView) this.findViewById(R.id.nome);
        TextView username = (TextView) this.findViewById(R.id.username);
        TextView email = (TextView) this.findViewById(R.id.email);
        ImageView imagem = (ImageView) this.findViewById(R.id.imagemPerfil);

        nome.setText(usuario.getNome());
        username.setText(usuario.getUsername());
        email.setText(usuario.getEmail());

        if (usuario.getImagem() != null && usuario.getImagem().length > 0 ) {
            Bitmap bitNew = BitmapFactory.decodeByteArray(usuario.getImagem(), 0, usuario.getImagem().length);

            imagem.setScaleType(ImageView.ScaleType.FIT_XY);
            imagem.setImageBitmap(bitNew);
        } else {
            imagem.setImageResource(R.drawable.perfil_icone);
        }

        salvarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarFotoPerfil();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarCamera();
            }
        });
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarGaleria();
            }
        });

    }
    public void chamarCamera(){
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        caminhoFoto = getExternalFilesDir(null) +  "/" + System.currentTimeMillis() +".jpg";
        arquivoFoto = new File(caminhoFoto);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));

        startActivityForResult(intentCamera, CODIGO_CAMERA);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream stream1 = null;
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_CAMERA) {
                foto = (ImageView) findViewById(R.id.imagemPerfil);
                Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
                bitmapReduzido.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bitMapData = stream.toByteArray();
                encodedImage = Base64.encodeToString(bitMapData,Base64.DEFAULT);
                Log.i("script", encodedImage);

                foto.setScaleType(ImageView.ScaleType.FIT_XY);
                foto.setImageBitmap(bitmapReduzido);
            }else if (requestCode == 1){
                foto = (ImageView) findViewById(R.id.imagemPerfil);
                try {
                    if (testeGaleria != null) {
                        testeGaleria.recycle();
                    }
                    stream1 = getContentResolver().openInputStream(data.getData());
                    testeGaleria = BitmapFactory.decodeStream(stream1);
                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    Bitmap scaled = Bitmap.createScaledBitmap(testeGaleria, 220, 220, true);
                    scaled.compress(Bitmap.CompressFormat.PNG, 100, stream2);
                    byte [] scaledByte = stream2.toByteArray();
                    encodedImage = Base64.encodeToString(scaledByte, Base64.DEFAULT);
                   /* int nh = (int) ( testeGaleria.getHeight() * (512.0 / testeGaleria.getWidth()) );
                    Bitmap scaled = Bitmap.createScaledBitmap(testeGaleria, 512, nh, true);*/
                    Log.i("script", encodedImage);
                    foto.setScaleType(ImageView.ScaleType.FIT_XY);
                    foto.setImageBitmap(scaled);

                }
                catch(FileNotFoundException e) {
                    e.printStackTrace();
                }
                finally {
                    if (stream1 != null)
                        try {
                            stream1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }

            }
        }
    }

    public void carregarGaleria(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    public void salvarFotoPerfil(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final JSONObject jsonobj = new JSONObject();
        try{
            jsonobj.put(KEY_PICTURE, encodedImage);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"123456789: " + e.getMessage(),Toast.LENGTH_LONG).show();
        }
        String url = REGISTER_URL + settings.getString("id", "");
        Log.i("script", jsonobj.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT,
                url, jsonobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i("script", "entrou no request!!");
                Toast.makeText(PerfilActivity.this, "Foto salva com sucesso!", Toast.LENGTH_LONG).show();
                Intent intentSalvatFoto = new Intent(PerfilActivity.this, DrawerActivity.class);
                startActivity(intentSalvatFoto);
                finish();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PerfilActivity.this,"!!"+error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){

        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


}
