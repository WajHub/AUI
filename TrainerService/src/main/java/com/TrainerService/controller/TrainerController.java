package com.TrainerService.controller;


import com.TrainerService.dto.request.PutTrainerDtoRequest;
import com.TrainerService.dto.request.UpdateTrainerNameRequest;
import com.TrainerService.dto.response.TrainerDtoResponse;
import com.TrainerService.mapper.Mapper;
import com.TrainerService.service.TrainerService;
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

    @GetMapping("api/trainers")
    public ResponseEntity<List<TrainerDtoResponse>> getTrainers(){
        return ResponseEntity.ok(trainerService.findAll()
                .stream()
                .map(trainer -> mapper.trainerToTrainerDtoResponse(trainer))
                .toList()
        );
    }

    @GetMapping("api/trainer/{trainerId}")
    public ResponseEntity<TrainerDtoResponse> getTrainer(@PathVariable UUID trainerId){
        return ResponseEntity.ok(
                mapper.trainerToTrainerDtoResponse(trainerService.findAllById(trainerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
        );
    }

    @PutMapping("api/trainer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainer(@PathVariable UUID id, @RequestBody PutTrainerDtoRequest trainerDtoRequest){
        trainerService.create(mapper.trainerDtoRequestToTrainer(id,trainerDtoRequest));
    }

    @PatchMapping("api/trainer/{trainerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateTrainerName(
            @RequestBody UpdateTrainerNameRequest trainerNameRequest,
            @PathVariable UUID trainerId){
        if(!trainerNameRequest.isValid()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        trainerService.findAllById(trainerId)
                .ifPresentOrElse(
                        (trainer -> {
                            trainer.setName(trainerNameRequest.getName());
                            trainerService.update(trainer); //Check if works (create)
                        }),
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

    @DeleteMapping("api/trainer/{trainerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable UUID trainerId){
        trainerService.findAllById(trainerId)
            .ifPresentOrElse(
                trainer -> trainerService.deleteById(trainerId),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
            );
    }

}
