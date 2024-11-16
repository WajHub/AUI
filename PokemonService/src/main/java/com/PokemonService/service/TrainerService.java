package com.PokemonService.service;

import com.PokemonService.model.Trainer;
import com.PokemonService.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainerService {
    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Optional<Trainer>findAllById(UUID id){
        return trainerRepository.findById(id);
    }

    public Trainer create(Trainer trainer){
        return trainerRepository.save(trainer);
    }

    public void delete(UUID id){
        trainerRepository.deleteById(id);
    }
}
