package com.PokemonService.repository;



import com.PokemonService.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {
    List<Pokemon> findAllByName(String name);

    Pokemon save(Pokemon pokemon);



}
