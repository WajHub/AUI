package com.PokemonService.controller;


import com.PokemonService.dto.request.PutPokemonDtoRequest;
import com.PokemonService.dto.request.UpdatePokemonLevelRequest;
import com.PokemonService.dto.response.PokemonDtoResponse;
import com.PokemonService.mapper.Mapper;
import com.PokemonService.service.PokemonService;
import com.PokemonService.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class PokemonController {
    private PokemonService pokemonService;
    private TrainerService trainerService;
    private Mapper mapper;

    @Autowired
    public PokemonController(PokemonService pokemonService, TrainerService trainerService, Mapper mapper) {
        this.pokemonService = pokemonService;
        this.trainerService = trainerService;
        this.mapper = mapper;
    }

    @GetMapping("api/pokemons")
    public ResponseEntity<List<PokemonDtoResponse>> getPokemons(){
        return ResponseEntity.ok(pokemonService.findAll()
                .stream()
                .map(pokemon -> mapper.pokemonToPokemonDtoResponse(pokemon))
                .toList()
        );
    }

    @GetMapping("api/pokemons/{pokemonId}")
    public ResponseEntity<PokemonDtoResponse> getPokemon(@PathVariable UUID pokemonId){
        return ResponseEntity.ok(
                mapper.pokemonToPokemonDtoResponse(pokemonService.findById(pokemonId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)))
        );
    }

    @PutMapping("api/pokemons/{pokemonId}")
    public void createPokemon(
            @RequestBody PutPokemonDtoRequest pokemonDtoRequest,
            @PathVariable UUID pokemonId){
        if(!pokemonDtoRequest.isValid()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        trainerService.findAllById(pokemonDtoRequest.getTrainer())
                .map((trainer) -> pokemonService.create(mapper.pokemonRequestToPokemon(pokemonId, pokemonDtoRequest)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("api/pokemons/{pokemonId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putPokemonLevel(
        @RequestBody UpdatePokemonLevelRequest pokemonLevelRequest,
        @PathVariable UUID pokemonId) {
        if (!pokemonLevelRequest.isValid()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        pokemonService.findById(pokemonId)
                .ifPresentOrElse(
                        (pokemon) -> {
                            pokemon.setLevel(pokemonLevelRequest.getLevel());
                            pokemonService.update(pokemon);
                        },
                        ()->{
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }

    @DeleteMapping("api/pokemons/{pokemonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable UUID pokemonId){
        pokemonService.findById(pokemonId)
            .ifPresentOrElse(
                pokemon -> pokemonService.deleteById(pokemonId),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
            );
    }
}
