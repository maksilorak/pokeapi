package com.movilespascual.pokeapi.paquete;

import com.movilespascual.pokeapi.models.pokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("pokemon?limit=20")
    Call<pokemonResponse> obtenerListaPokemon();
}
