package com.TrainerService.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "trainers")
@NoArgsConstructor
@AllArgsConstructor
public class Trainer implements Serializable, Comparable<Trainer> {

    @Id
    @ToString.Exclude
    @Builder.Default
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "age")
    private int age;

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
