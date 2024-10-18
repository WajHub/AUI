package org.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = "pokemons")
@Builder
public class Trainer implements Serializable, Comparable<Trainer> {

    private String name;

    private int age;

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
