package org.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = "trainer")
@Builder
public class Pokemon implements Serializable, Comparable<Pokemon> {

    private String name;

    private int level;

//    private List<TypePokemon> types;

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
