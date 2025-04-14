package com.example.consumirapi;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FrmverPelicula extends AppCompatActivity {

    private ImageView imagen;
    private TextView lblcodigo, lbltitulo, lblfecha, lbldescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frmverpelicula);

        imagen = findViewById(R.id.banner);
        lblcodigo = findViewById(R.id.lblid);
        lbltitulo = findViewById(R.id.lbltitulo);
        lblfecha = findViewById(R.id.lblestreno);
        lbldescripcion = findViewById(R.id.lbldescripcion);

        long id = getIntent().getLongExtra("id", 0);
        String titulo = getIntent().getStringExtra("titulo");
        String poster = getIntent().getStringExtra("imagen");
        String fecha = getIntent().getStringExtra("fecha");
        String descripcion = getIntent().getStringExtra("descripcion");

        lblcodigo.setText("ID: " + id);
        lbltitulo.setText("Título: " + titulo);
        lblfecha.setText("Estreno: " + fecha);
        lbldescripcion.setText("Descripción: " + descripcion);

        String urlImagen = "https://image.tmdb.org/t/p/original/" + poster;
        Glide.with(this).load(urlImagen).into(imagen);
    }
}
