package com.example.PokemonWorld.service;

import com.example.PokemonWorld.model.Pokemon;
import com.example.PokemonWorld.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PokemonService {
    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon create(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public List<Pokemon> findAll(){
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> findById(UUID id) { return pokemonRepository.findById(id);}

    public void update(Pokemon pokemon) { pokemonRepository.save(pokemon);}

    public void deleteById(UUID id) {
        pokemonRepository.deleteById(id);
    }
}
