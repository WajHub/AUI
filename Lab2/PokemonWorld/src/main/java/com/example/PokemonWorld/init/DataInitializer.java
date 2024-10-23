package com.example.PokemonWorld.init;

import com.example.PokemonWorld.mapper.Mapper;
import com.example.PokemonWorld.model.Pokemon;
import com.example.PokemonWorld.model.Trainer;
import com.example.PokemonWorld.service.PokemonService;
import com.example.PokemonWorld.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
public class DataInitializer implements CommandLineRunner {

    private PokemonService pokemonService;
    private TrainerService trainerService;
    private Mapper mapper;

    @Autowired
    public DataInitializer(PokemonService pokemonService, TrainerService trainerService, Mapper mapper) {
        this.pokemonService = pokemonService;
        this.trainerService = trainerService;
        this.mapper = mapper;
    }

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

    private void printAll(){
        trainerService.findAll().forEach((trainer ->{
            System.out.println(mapper.trainerToTrainerDto(trainer));
        }));
        pokemonService.findAll().forEach((pokemon -> {
            System.out.println(mapper.pokemonToPokemonDto(pokemon));
        }));
    }

    @Override
    public void run(String... args) throws Exception {
        initData();
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean isRunning = true;

        while(isRunning){
            printAll();
            System.out.println("Write Command:");
            command = scanner.next();
            switch (command) {
                case "show":
                    printAll();
                    break;
                case "create_trainer":
                    System.out.print("[Creating Trainer...]Name:");
                    String nameTrainer = scanner.next();
                    System.out.print("[Creating Trainer...]Age:");
                    int ageTrainer = scanner.nextInt();
                    trainerService.create(Trainer.builder()
                                            .name(nameTrainer)
                                            .age(ageTrainer)
                                        .build());
                    break;
                case "create_pokemon":
                    System.out.print("[Creating Pokemon...]Name:");
                    String namePokemon = scanner.next();
                    System.out.print("[Creating Trainer...]Level:");
                    int levelPokemon = scanner.nextInt();
                    System.out.print("[Creating Trainer...]Trainer UUID:");
                    UUID idTrainer = UUID.fromString(scanner.next());
                    Optional<Trainer> trainer = trainerService.findAllById(idTrainer);
                    if(trainer.isEmpty()){
                        System.out.println("Trainer doesn't exist!");
                        break;
                    }
                    pokemonService.create(Pokemon.builder()
                                            .name(namePokemon)
                                            .level(levelPokemon)
                                            .trainer(trainer.get())
                                        .build());
                    break;
                case "delete_trainer":
                    System.out.print("[Deleting Trainer...]Trainer UUID:");
                    UUID idTrainerToDelete = UUID.fromString(scanner.next());
                    trainerService.deleteById(idTrainerToDelete);
                    break;
                case "delete_pokemon":
                    System.out.print("[Deleting Pokemon...]Pokemon UUID:");
                    UUID idPokemonToDelete = UUID.fromString(scanner.next());
                    pokemonService.deleteById(idPokemonToDelete);
                    break;
                case "help":
                    System.out.println("show - print out all fo categories and elements");
                    System.out.println("create_trainer - create new category");
                    System.out.println("create_pokemon - create new element");
                    System.out.println("delete_trainer - delete category");
                    System.out.println("delete_pokemon - delete element");
                    System.out.println("Exit - end program");
                case "exit":
                    System.out.println("Exit");
                    isRunning = false;
                    break;

            }
        }
    }
}
