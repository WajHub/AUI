package com.PokemonService.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "pokemons")
@Builder
@Entity
@Table(name = "trainers")
@NoArgsConstructor
@AllArgsConstructor
public class Trainer implements Serializable {

    @Id
    @Builder.Default
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private List<Pokemon> pokemons = new ArrayList<>();

    public static Trainer getById(List<Trainer> trainers, String uuid) {
        return trainers.stream()
                .filter((trainer -> trainer.getId().toString().equals(uuid.toString())))
                .findFirst()
                .orElse(null);
    }
}
