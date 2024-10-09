package org.example.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = "characters")
@Builder
public class Profession implements Comparable, Serializable {
    private String name;
    private int baseArmor;
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
        return Objects.hash(name, baseArmor);
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public static Profession getByName(List<Profession> professions, String name){
        return professions.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }

}
