package com.TrainerService.mapper;

import com.TrainerService.dto.request.PutTrainerDtoRequest;
import com.TrainerService.dto.response.TrainerDtoResponse;
import com.TrainerService.model.Trainer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Mapper {

    public TrainerDtoResponse trainerToTrainerDtoResponse(Trainer trainer){
        return TrainerDtoResponse.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .age(trainer.getAge())
                .build();
    }

    public Trainer trainerDtoRequestToTrainer(UUID id, PutTrainerDtoRequest trainerDtoRequest) {
        return Trainer.builder()
                .id(id)
                .name(trainerDtoRequest.getName())
                .age(trainerDtoRequest.getAge())
                .build();
    }

    public Trainer trainerDtoRequestToTrainer(PutTrainerDtoRequest trainerDtoRequest) {
        return Trainer.builder()
                .name(trainerDtoRequest.getName())
                .age(trainerDtoRequest.getAge())
                .build();
    }
}
