package com.example.PokemonWorld.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "trainer")
@Builder
@Entity
@Table(name = "pokemons")
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon implements Serializable, Comparable<Pokemon> {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name ="level")
    private int level;

    @ManyToOne
    @JoinColumn(name="trianer")
    private Trainer trainer;

    @Override
    public int compareTo(Pokemon pokemon) {
        if(this.getLevel() == pokemon.getLevel()) return this.name.compareTo(pokemon.getName());
        return this.getLevel() - (pokemon.getLevel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }
}
