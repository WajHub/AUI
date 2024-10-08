package org.example;
import org.example.model.Profession;
import org.example.model.Character;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Profession profession = Profession.builder()
                .name("Warrior")
                .baseArmor(50)
                .build();

        Profession profession2 = Profession.builder()
                .name("Mag")
                .baseArmor(20)
                .build();

        Character character1 = Character.builder()
                .name("Hubert")
                .level(20)
                .profession(profession)
                .build();

        Character character2 = Character.builder()
                .name("Postac 2 ")
                .level(50)
                .profession(profession)
                .build();

        Character character3 = Character.builder()
                .name("Postac 3")
                .level(10)
                .profession(profession2)
                .build();

        profession.addCharacter(character1);
        profession.addCharacter(character2);
        profession2.addCharacter(character3);

        List<Profession> professions = Arrays.asList(profession, profession2);

        professions.forEach(prof -> {
            System.out.println(prof);
            prof.getCharacters().forEach(System.out::println);
        });
        System.out.println("--------------------");

    }
}