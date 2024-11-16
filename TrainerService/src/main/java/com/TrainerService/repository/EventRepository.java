package com.TrainerService.repository;

import com.TrainerService.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class EventRepository {

    private final RestTemplate restTemplate;
    @Autowired
    public EventRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void create(UUID id){
        restTemplate.put("/api/trainer/{id}",null , id);
    }

    public void delete(UUID trainerId) {
        restTemplate.delete("api/trainer/{trainerId}", trainerId);
    }
}
