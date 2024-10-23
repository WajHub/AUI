package com.example.PokemonWorld.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "pokemons")
@Builder
@Entity
@Table(name = "trainers")
@NoArgsConstructor
@AllArgsConstructor
public class Trainer implements Serializable, Comparable<Trainer> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Pokemon> pokemons = new ArrayList<>();

    @Override
    public int compareTo(Trainer trainer) {
        if(this.getAge() == trainer.getAge()) return this.name.compareTo(trainer.getName());
        return this.getAge() - (trainer.getAge());
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static Trainer getByName(List<Trainer> trainers, String name){
        return trainers.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
    }
}
