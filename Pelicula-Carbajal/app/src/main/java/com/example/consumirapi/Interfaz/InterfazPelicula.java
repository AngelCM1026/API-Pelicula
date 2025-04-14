package com.example.consumirapi.Interfaz;

import com.example.consumirapi.Modelo.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfazPelicula {
    @GET("popular")
    Call<Resultado> obtenerdatos(@Query("api_key") String apiKey,@Query("page") long page);
}
