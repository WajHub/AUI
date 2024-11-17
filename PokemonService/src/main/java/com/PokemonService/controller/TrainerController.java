package com.PokemonService.controller;


import com.PokemonService.mapper.Mapper;
import com.PokemonService.model.Trainer;
import com.PokemonService.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class TrainerController {

    private TrainerService trainerService;
    private Mapper mapper;

    @Autowired
    public TrainerController(TrainerService trainerService, Mapper mapper) {
        this.trainerService = trainerService;
        this.mapper = mapper;
    }

    @PutMapping("api/trainers/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainer(@PathVariable UUID id){
        trainerService.create(Trainer.builder().id(id).build());
    }


    @DeleteMapping("api/trainers/{trainerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable UUID trainerId){
        trainerService.findAllById(trainerId)
            .ifPresentOrElse(
                trainer -> trainerService.delete(trainerId),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
            );
    }

}
