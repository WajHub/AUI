package com.TrainerService.repository;


import com.TrainerService.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainerRepository extends JpaRepository<Trainer, UUID> {

    Trainer save(Trainer trainer);

}
