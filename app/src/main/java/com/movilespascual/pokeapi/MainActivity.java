package com.movilespascual.pokeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.movilespascual.pokeapi.models.Pokemon;
import com.movilespascual.pokeapi.models.pokemonResponse;
import com.movilespascual.pokeapi.paquete.APIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private static final String TAG = "POKEMONRESPONSE";
    private RecyclerView recyclerView;
    private pokemonListAdapter listaPokemonAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewPokemon);
        listaPokemonAdapt = new pokemonListAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapt);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        obtenerDatos();
    }

    private void obtenerDatos() {
        //instancia del servicio
        APIService service = retrofit.create(APIService.class);
        Call<pokemonResponse> pokemonResponseCall = service.obtenerListaPokemon();

        pokemonResponseCall.enqueue(new Callback<pokemonResponse>() {
            //respuesta a la consulta
            @Override
            public void onResponse(Call<pokemonResponse> call, Response<pokemonResponse> response) {
                if(response.isSuccessful()){
                    pokemonResponse respuesta = response.body();
                    ArrayList<Pokemon> listPokemon = respuesta.getResults();

                    listaPokemonAdapt.adicionarListaPokemon(listPokemon);
                    for (int i = 0; i < listPokemon.size(); i++) {
                        Pokemon poke = listPokemon.get(i);
                        Log.i(TAG,"pokemon:"+poke.getName());
                    }
                }else{
                    Log.e(TAG,"onResponse"+response.errorBody());
                }
            }
            //cuando ocurre algun error
            @Override
            public void onFailure(Call<pokemonResponse> call, Throwable t) {
                Log.e(TAG,"onFailure"+t.getMessage());
            }
        });
    }
}