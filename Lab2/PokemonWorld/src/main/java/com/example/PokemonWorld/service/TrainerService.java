package com.example.PokemonWorld.service;

import com.example.PokemonWorld.model.Trainer;
import com.example.PokemonWorld.repository.TrainerRepository;
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

    public Trainer create(Trainer trainer){
        return trainerRepository.save(trainer);
    }
    public List<Trainer> findAll(){
        return trainerRepository.findAll();
    }

    public Optional<Trainer>findAllById(UUID id){
        return trainerRepository.findById(id);
    }

    public void deleteById(UUID id){
        trainerRepository.deleteById(id);
    }
}
