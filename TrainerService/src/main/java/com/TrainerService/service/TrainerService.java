package com.TrainerService.service;


import com.TrainerService.model.Trainer;
import com.TrainerService.repository.EventRepository;
import com.TrainerService.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainerService {
    private TrainerRepository trainerRepository;
    private EventRepository eventRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, EventRepository eventRepository) {
        this.trainerRepository = trainerRepository;
        this.eventRepository = eventRepository;
    }

    public Trainer create(Trainer trainer){
        eventRepository.create(trainer.getId());
        return trainerRepository.save(trainer);
    }
    public List<Trainer> findAll(){
        return trainerRepository.findAll();
    }

    public Optional<Trainer>findAllById(UUID id){
        return trainerRepository.findById(id);
    }

    public void update(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    public void deleteById(UUID id){
        eventRepository.delete(id);
        trainerRepository.deleteById(id);
    }
}
