package com.movilespascual.pokeapi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.movilespascual.pokeapi.models.Pokemon;

import java.util.ArrayList;

public class pokemonListAdapter extends RecyclerView.Adapter<pokemonListAdapter.ViewHolder> {

    private ArrayList<Pokemon> datos;
    Context context;

    public pokemonListAdapter(Context context){
        this.context = context;
        datos = new ArrayList<>();
    }


    @androidx.annotation.NonNull
    @Override
    public ViewHolder onCreateViewHolder( @androidx.annotation.NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_pokemon,viewGroup,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder( @androidx.annotation.NonNull ViewHolder viewHolder, int i) {
        Pokemon pokemon = datos.get(i);
        viewHolder.nombrePokemon.setText(pokemon.getName());
        //usando la libreria glide
        //Glide.with(context)
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listPokemon) {
        datos.addAll(listPokemon);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView fotoPokemon;
        private TextView nombrePokemon;

        public ViewHolder(View itemView){
            super(itemView);
            fotoPokemon = itemView.findViewById(R.id.fotoPokemon);
            nombrePokemon = itemView.findViewById(R.id.nombrePokemon);
        }
    }

}
