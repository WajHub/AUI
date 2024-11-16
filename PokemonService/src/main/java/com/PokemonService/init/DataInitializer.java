package com.PokemonService.init;


import com.PokemonService.mapper.Mapper;
import com.PokemonService.model.Pokemon;
import com.PokemonService.model.Trainer;
import com.PokemonService.repository.PokemonRepository;
import com.PokemonService.repository.TrainerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    private PokemonRepository pokemonRepository;
    private TrainerRepository trainerRepository;
    private Mapper mapper;

    @Autowired
    public DataInitializer(PokemonRepository pokemonRepository, TrainerRepository trainerRepository, Mapper mapper) {
        this.pokemonRepository = pokemonRepository;
        this.trainerRepository = trainerRepository;

        this.mapper = mapper;
    }
    @PostConstruct
    private void initData(){
        System.out.println("INITIALIZING DATA");

        List<Trainer> trainers = Stream.of(
                Trainer.builder()
                        .id(UUID.fromString("b6bbff21-71c4-492f-af34-edaafb16d3e4"))
                        .build(),
                Trainer.builder()
                        .id(UUID.fromString("3b25be7c-a2ba-4240-8723-969fb130fa22"))
                        .build()
        ).toList();

        List<Pokemon> pokemons = Stream.of(
                Pokemon.builder()
                        .id(UUID.fromString("e64873b4-16a2-486d-8edc-708ca541439c"))
                        .name("Pikachu")
                        .level(50)
                        .trainer(Trainer.getById(trainers, "b6bbff21-71c4-492f-af34-edaafb16d3e4"))
                        .build(),
                Pokemon.builder()
                        .id(UUID.fromString("074f2423-1f0b-40f3-ad00-751797832ffa"))
                        .name("Charizard")
                        .level(36)
                        .trainer(Trainer.getById(trainers, "b6bbff21-71c4-492f-af34-edaafb16d3e4"))
                        .build(),
                Pokemon.builder()
                        .id(UUID.fromString("64c28f24-1645-433a-b1ac-df66c69675ef"))
                        .name("Starmie")
                        .level(30)
                        .trainer(Trainer.getById(trainers, "3b25be7c-a2ba-4240-8723-969fb130fa22"))
                        .build(),
                Pokemon.builder()
                        .id(UUID.fromString("5aad8e0f-4d81-406f-b024-6f7d311e3a82"))
                        .name("Psyduck")
                        .level(10)
                        .trainer(Trainer.getById(trainers, "3b25be7c-a2ba-4240-8723-969fb130fa22"))
                        .build()
        ).toList();

        trainers.forEach((trainer) -> trainerRepository.save(trainer));
        pokemons.forEach((pokemon -> pokemonRepository.save(pokemon)));

    }

}
