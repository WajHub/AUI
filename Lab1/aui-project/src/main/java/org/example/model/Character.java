package org.example.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;


@Getter
@ToString
@Builder
public class Character implements Comparable, Serializable {
    private String name;
    private int level;
    Profession profession;

    @Override
    public int compareTo(Object o) {
        if(o==null) return 0;
        if(o.getClass()!=this.getClass()) return 0;
        Character character = (Character) o;
        return this.getLevel() - (character.getLevel());
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }


}
