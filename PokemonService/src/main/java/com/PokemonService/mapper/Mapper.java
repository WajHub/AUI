package com.PokemonService.mapper;

import com.PokemonService.dto.request.PutPokemonDtoRequest;
import com.PokemonService.dto.response.PokemonDtoResponse;
import com.PokemonService.model.Pokemon;
import com.PokemonService.model.Trainer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class Mapper {

    public PokemonDtoResponse pokemonToPokemonDtoResponse(Pokemon pokemon){
        return PokemonDtoResponse.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .level(pokemon.getLevel())
                .trainerId(pokemon.getTrainer().getId())
            .build();
    }



    public Pokemon pokemonRequestToPokemon(UUID pokemonId, PutPokemonDtoRequest pokemonDtoRequest) {
        return Pokemon.builder()
                .id(pokemonId)
                .name(pokemonDtoRequest.getName())
                .level(pokemonDtoRequest.getLevel())
                .trainer(Trainer.builder()
                        .id(pokemonDtoRequest.getTrainer())
                        .build())
                .build();
    }

    public Pokemon pokemonRequestToPokemon(PutPokemonDtoRequest pokemonDtoRequest) {
        return Pokemon.builder()
                .name(pokemonDtoRequest.getName())
                .level(pokemonDtoRequest.getLevel())
                .trainer(Trainer.builder()
                        .id(pokemonDtoRequest.getTrainer())
                        .build())
                .build();
    }
}
