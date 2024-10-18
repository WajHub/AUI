package com.example.PokemonWorld.repository;

import com.example.PokemonWorld.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TrainerRepository extends JpaRepository<Trainer, UUID> {
    Trainer findAllByName(String name);

    Trainer save(Trainer trainer);

    boolean existsByName(String name);

}
