package com.example.PokemonWorld.init;

import com.example.PokemonWorld.mapper.Mapper;
import com.example.PokemonWorld.model.Pokemon;
import com.example.PokemonWorld.model.Trainer;
import com.example.PokemonWorld.service.PokemonService;
import com.example.PokemonWorld.service.TrainerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    private PokemonService pokemonService;
    private TrainerService trainerService;
    private Mapper mapper;

    @Autowired
    public DataInitializer(PokemonService pokemonService, TrainerService trainerService, Mapper mapper) {
        this.pokemonService = pokemonService;
        this.trainerService = trainerService;
        this.mapper = mapper;
    }
    @PostConstruct
    private void initData(){
        System.out.println("INITIALIZING DATA");
        List<Trainer> trainers = Stream.of(
                Trainer.builder()
                        .name("Ash")
                        .age(10)
                        .pokemons(new ArrayList<>())
                        .build(),
                Trainer.builder()
                        .name("Misty")
                        .age(12)
                        .pokemons(new ArrayList<>())
                        .build()
        ).toList();

        List<Pokemon> pokemons = Stream.of(
                Pokemon.builder()
                        .name("Pikachu")
                        .level(50)
                        .trainer(Trainer.getByName(trainers, "Ash"))
                        .build(),
                Pokemon.builder()
                        .name("Charizard")
                        .level(36)
                        .trainer(Trainer.getByName(trainers, "Ash"))
                        .build(),
                Pokemon.builder()
                        .name("Starmie")
                        .level(30)
                        .trainer(Trainer.getByName(trainers, "Misty"))
                        .build(),
                Pokemon.builder()
                        .name("Psyduck")
                        .level(10)
                        .trainer(Trainer.getByName(trainers, "Misty"))
                        .build()
        ).toList();

        trainers.forEach(trainer -> trainer.getPokemons()
                .addAll(pokemons.stream()
                        .filter(pokemon -> pokemon.getTrainer().getName().equals(trainer.getName()))
                        .toList())
        );

        trainers.stream().forEach((trainer) -> trainerService.create(trainer));
        pokemons.stream().forEach((pokemon -> pokemonService.create(pokemon)));

    }

}
