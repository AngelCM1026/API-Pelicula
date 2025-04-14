package com.example.consumirapi.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.consumirapi.FrmverPelicula;
import com.example.consumirapi.Modelo.Pelicula;
import com.example.consumirapi.R;

import java.util.ArrayList;

public class AdaptadorPelicula extends RecyclerView.Adapter<AdaptadorPelicula.ViewHolder> {
    private ArrayList<Pelicula> data;
    private Context c;

    public AdaptadorPelicula(Context c){
        this.data = new ArrayList<>();
        this.c = c;
    }

    public void adicionarregistros(ArrayList<Pelicula> peliculas){
        data.addAll(peliculas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPelicula.ViewHolder holder, int position) {
        Pelicula pelicula = data.get(position);
        holder.codigo.setText(String.valueOf(pelicula.getId()));
        holder.nombre.setText(pelicula.getOriginal_title());


        String urlImagen = "https://image.tmdb.org/t/p/original/" + pelicula.getPoster_path();
        Glide.with(c).load(urlImagen).centerCrop().into(holder.figura);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView figura;
        private TextView codigo, nombre, txtpos;

        public ViewHolder(@NonNull View velemento) {
            super(velemento);
            figura = velemento.findViewById(R.id.imagen);
            codigo = velemento.findViewById(R.id.txtcodigo);
            nombre = velemento.findViewById(R.id.txttitulo);



            velemento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Pelicula pelicula = data.get(position);
                        Intent ven = new Intent(v.getContext(), FrmverPelicula.class);
                        ven.putExtra("id", pelicula.getId());
                        ven.putExtra("titulo", pelicula.getOriginal_title());
                        ven.putExtra("imagen", pelicula.getPoster_path());
                        ven.putExtra("fecha", pelicula.getRelease_date());
                        ven.putExtra("descripcion", pelicula.getOverview());
                        v.getContext().startActivity(ven);
                    }
                }
            });
        }
    }
}
