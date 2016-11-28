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

public class PerfilActivity extends AppCompatActivity {
    public static final String REGISTER_URL = "https://cookfy.herokuapp.com/users/";
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_PICTURE = "picture";
    private static final int CODIGO_CAMERA = 567;
    private Button camera;
    private String caminhoFoto;
    private  String encodedImage;
    private Button salvarFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        camera = (Button) findViewById(R.id.cameraPerfil);
        salvarFoto = (Button) findViewById((R.id.salvarFotoPerfil));
        Intent intent = getIntent();
        User usuario = (User) intent.getSerializableExtra("usuario");

        TextView nome = (TextView) this.findViewById(R.id.nome);
        TextView username = (TextView) this.findViewById(R.id.username);
        TextView email = (TextView) this.findViewById(R.id.email);

        nome.setText(usuario.getNome());
        username.setText(usuario.getUsername());
        email.setText(usuario.getEmail());
        salvarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarFotoPerfil();
            }
        });

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
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
                bitmapReduzido.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bitMapData = stream.toByteArray();
                encodedImage = Base64.encodeToString(bitMapData,Base64.DEFAULT);
                Log.i("script", encodedImage);

                foto.setScaleType(ImageView.ScaleType.CENTER_CROP);
                foto.setImageBitmap(bitmapReduzido);
            }
        }
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
}
