package org.example.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@ToString
@Builder
public class Profession implements Comparable{
    private String name;
    private int baseArmor;
    @Builder.Default
    private List<Character> characters = new ArrayList<>();
    @Override
    public int compareTo(Object o) {
        if(o==null) return 0;
        if(o.getClass()!=this.getClass()) return 0;
        Profession profession = (Profession) o;
        return this.getBaseArmor() - (profession.getBaseArmor());
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, baseArmor, characters);
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

}
