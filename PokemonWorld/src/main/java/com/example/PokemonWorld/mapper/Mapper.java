package com.example.PokemonWorld.mapper;

import com.example.PokemonWorld.dto.TrainerDto;
import com.example.PokemonWorld.model.Pokemon;
import com.example.PokemonWorld.model.Trainer;
import org.example.dto.PokemonDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public PokemonDto pokemonToPokemonDto(Pokemon pokemon){
        return PokemonDto.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .trainerName(pokemon.getTrainer().getName())
                .level(pokemon.getLevel())
                .build();
    }

    public TrainerDto trainerToTrainerDto(Trainer trainer){
        return TrainerDto.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .age(trainer.getAge())
                .pokemonDtoList(trainer.getPokemons()
                                .stream()
                                .map((this::pokemonToPokemonDto))
                        .toList())
                .build();
    }
}
