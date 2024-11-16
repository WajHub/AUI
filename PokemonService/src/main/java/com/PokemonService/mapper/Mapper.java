package com.PokemonService.mapper;

import com.PokemonService.dto.request.PutPokemonDtoRequest;
import com.PokemonService.dto.response.PokemonDtoResponse;
import com.PokemonService.model.Pokemon;
import com.PokemonService.model.Trainer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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



    public Pokemon pokemonRequestToPokemon(PutPokemonDtoRequest pokemonDtoRequest, Trainer trainer) {
        return Pokemon.builder()
                .name(pokemonDtoRequest.getName())
                .level(pokemonDtoRequest.getLevel())
                .trainer(trainer)
                .build();
    }
}
