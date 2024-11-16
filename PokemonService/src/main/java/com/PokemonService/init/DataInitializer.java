package com.PokemonService.init;


import com.PokemonService.mapper.Mapper;
import com.PokemonService.model.Pokemon;
import com.PokemonService.model.Trainer;
import com.PokemonService.service.PokemonService;
import com.PokemonService.service.TrainerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
//        System.out.println("INITIALIZING DATA");
//        List<Trainer> trainers = Stream.of(
//                Trainer.builder()
//                        .name("Ash")
//                        .pokemons(new ArrayList<>())
//                        .build(),
//                Trainer.builder()
//                        .name("Misty")
//                        .pokemons(new ArrayList<>())
//                        .build()
//        ).toList();
//
//        List<Pokemon> pokemons = Stream.of(
//                Pokemon.builder()
//                        .name("Pikachu")
//                        .level(50)
//                        .trainer(Trainer.getByName(trainers, "Ash"))
//                        .build(),
//                Pokemon.builder()
//                        .name("Charizard")
//                        .level(36)
//                        .trainer(Trainer.getByName(trainers, "Ash"))
//                        .build(),
//                Pokemon.builder()
//                        .name("Starmie")
//                        .level(30)
//                        .trainer(Trainer.getByName(trainers, "Misty"))
//                        .build(),
//                Pokemon.builder()
//                        .name("Psyduck")
//                        .level(10)
//                        .trainer(Trainer.getByName(trainers, "Misty"))
//                        .build()
//        ).toList();
//
//        trainers.forEach(trainer -> trainer.getPokemons()
//                .addAll(pokemons.stream()
//                        .filter(pokemon -> pokemon.getTrainer().getName().equals(trainer.getName()))
//                        .toList())
//        );
//
//        trainers.stream().forEach((trainer) -> trainerService.create(trainer));
//        pokemons.stream().forEach((pokemon -> pokemonService.create(pokemon)));

    }

}
