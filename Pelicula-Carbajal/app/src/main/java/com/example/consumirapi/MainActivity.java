package com.example.consumirapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.consumirapi.Adaptador.AdaptadorPelicula;
import com.example.consumirapi.Interfaz.InterfazPelicula;
import com.example.consumirapi.Modelo.Pelicula;
import com.example.consumirapi.Modelo.Resultado;
import com.example.consumirapi.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding p;
    AdaptadorPelicula af;
    boolean cargar;
    int pagina;
    Retrofit rfit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(p.getRoot());

        af = new AdaptadorPelicula(this);
        p.listado.setAdapter(af);

        GridLayoutManager grid = new GridLayoutManager(this, 2);
        p.listado.setLayoutManager(grid);

        p.listado.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = grid.getChildCount();
                    int totalItemCount = grid.getItemCount();
                    int firstVisibleItemPosition = grid.findFirstVisibleItemPosition();

                    if (cargar && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                        cargar = false;
                        pagina++;
                        obtener(pagina);
                    }
                }
            }
        });

        rfit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cargar = true;
        pagina = 1; // Inicia en la página 1
        obtener(pagina);
    }

    private void obtener(int page) {
        InterfazPelicula s = rfit.create(InterfazPelicula.class);
        Call<Resultado> respuesta = s.obtenerdatos("7be72508776961f3948639fbd796bccd", page);
        respuesta.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                cargar = true;
                if (response.isSuccessful()) {
                    Resultado res = response.body();
                    ArrayList<Pelicula> lista = res.getResults();
                    af.adicionarregistros(lista);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable throwable) {
                cargar = true;
            }
        });
    }
}
