package com.example.PokemonWorld.mapper;


import com.example.PokemonWorld.dto.request.PutPokemonDtoRequest;
import com.example.PokemonWorld.dto.request.PutTrainerDtoRequest;
import com.example.PokemonWorld.dto.response.PokemonDtoResponse;
import com.example.PokemonWorld.dto.response.TrainerDtoResponse;
import com.example.PokemonWorld.model.Pokemon;
import com.example.PokemonWorld.model.Trainer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Mapper {

    public PokemonDtoResponse pokemonToPokemonDtoResponse(Pokemon pokemon){
        return PokemonDtoResponse.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .level(pokemon.getLevel())
                .trainerName(pokemon.getTrainer().getName())
            .build();
    }

    public TrainerDtoResponse trainerToTrainerDtoResponse(Trainer trainer){
        return TrainerDtoResponse.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .age(trainer.getAge())
                .pokemonDtoList(trainer.getPokemons()
                        .stream()
                        .map(this::pokemonToPokemonDtoResponse)
                        .toList())
                .build();
    }

    public Trainer trainerDtoRequestToTrainer(PutTrainerDtoRequest trainerDtoRequest) {
        return Trainer.builder()
                .name(trainerDtoRequest.getName())
                .age(trainerDtoRequest.getAge())
                .pokemons(new ArrayList<>())
                .build();
    }

    public Pokemon pokemonRequestToPokemon(PutPokemonDtoRequest pokemonDtoRequest, Trainer trainer) {
        return Pokemon.builder()
                .name(pokemonDtoRequest.getName())
                .level(pokemonDtoRequest.getLevel())
                .trainer(trainer)
                .build();
    }
}
