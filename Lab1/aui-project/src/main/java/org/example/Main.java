package org.example;

import org.example.dto.PokemonDto;
import org.example.model.Pokemon;
import org.example.model.Trainer;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

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

        System.out.println("Task 2");
        trainers.forEach(trainer -> {
            System.out.println(trainer);
            trainer.getPokemons().forEach(System.out::println);
        });
        System.out.println("--------------------");

        // Task 3: Collect all pokemons into a Set and print
        System.out.println("Task 3");
        Set<Pokemon> pokemonSet = trainers.stream()
                .flatMap(trainer -> trainer.getPokemons().stream())
                .collect(Collectors.toSet());
        pokemonSet.forEach(System.out::println);
        System.out.println("--------------------");

        // Task 4: Filter pokemons with level > 15, sort by name, and print
        System.out.println("Task 4");
        pokemonSet.stream()
                .filter(pokemon -> pokemon.getLevel() > 15)
                .sorted(Comparator.comparing(Pokemon::getName))
                .forEach(System.out::println);
        System.out.println("--------------------");

        // Task 5: Convert Pokemon objects to PokemonDto and print
        System.out.println("Task 5");
        List<PokemonDto> pokemonDtos = pokemonSet.stream()
                .map(pokemon -> new PokemonDto(pokemon.getName(), pokemon.getLevel(), pokemon.getTrainer().getName()))
                .toList();
        pokemonDtos.forEach(System.out::println);
        System.out.println("--------------------");

        // Task 6: Serialize trainers to a file
        System.out.println("Task 6");
        String filename = "trainers_ser.bin";
        try (FileOutputStream file = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(file)) {

            out.writeObject(trainers);
            System.out.println("Serialized!");

        } catch (IOException e) {
            System.out.println("Serialization Error");
            throw new RuntimeException(e);
        }

        // Deserialize trainers from file
        List<Trainer> trainers_Deserialize = null;
        try (FileInputStream file = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(file)) {

            trainers_Deserialize = (List<Trainer>) in.readObject();
            System.out.println("Deserialized!");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization Error");
            throw new RuntimeException(e);
        }

        trainers_Deserialize.forEach(System.out::println);
        System.out.println("--------------------");

        // Task 7: Heal all pokemons in parallel using ForkJoinPool
        System.out.println("Task 7");
        ForkJoinPool forkJoinPool = new ForkJoinPool(1);

        forkJoinPool.submit(() -> {
            pokemonSet.parallelStream().forEach((pokemon) -> {
                System.out.println("Healing Pokemon " + pokemon.getName());
                System.out.println("...");
                try {
                    Thread.sleep(2000);  // Simulate time-consuming healing process
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        forkJoinPool.submit(() -> System.out.println("Healing Complete!"));

        forkJoinPool.close();
    }
}
