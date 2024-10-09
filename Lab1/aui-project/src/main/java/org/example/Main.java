package org.example;
import org.example.dto.CharacterDto;
import org.example.model.Profession;
import org.example.model.Character;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;


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

        Character character4 = Character.builder()
                .name("AA")
                .level(19)
                .profession(profession2)
                .build();

        profession.addCharacter(character1);
        profession.addCharacter(character2);
        profession.addCharacter(character4);
        profession2.addCharacter(character3);

        List<Profession> professions = Arrays.asList(profession, profession2);

        System.out.println("Zadanie 2");
        professions.forEach(prof -> {
            System.out.println(prof);
            prof.getCharacters().forEach(System.out::println);
        });
        System.out.println("--------------------");

        System.out.println("Zadanie 3");
        Set<Character> characters = professions.stream().flatMap(prof -> prof.getCharacters().stream()).collect(Collectors.toSet());
        characters.forEach(System.out::println);
        System.out.println("--------------------");

        System.out.println("Zadanie 4");
        characters.stream().filter(c -> c.getLevel()> 15).sorted(Comparator.comparing(Character::getName)).forEach(System.out::println);
        System.out.println("--------------------");

        System.out.println("Zadanie 5");
        List<CharacterDto> characterDtos = characters.stream().map(c -> new CharacterDto(c.getName(), c.getLevel(),c.getProfession().getName())).toList();
        characterDtos.forEach(System.out::println);

        System.out.println("Zadanie 6");
        String filename = "professions_ser.bin";
        try
        {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(professions);

            out.close();
            file.close();

            System.out.println("Serialized!");
        }
        catch (IOException e) {
            System.out.println("ERR");
            throw new RuntimeException(e);
        }
        List<Profession> professions_Deserialize = null;
        try
        {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            professions_Deserialize  =  (List<Profession>)in.readObject();

            in.close();
            file.close();

            System.out.println("Deserialized!");
        } catch(IOException | ClassNotFoundException e)
        {
            System.out.println("ERR");
            throw new RuntimeException(e);
        }

        professions_Deserialize.forEach(System.out::println);
        System.out.println("--------------------");

        System.out.println("Zadanie 7");

        ForkJoinPool forkJoinPool = new ForkJoinPool(1);

        forkJoinPool.submit(() -> {
            characters.parallelStream().forEach((c) -> {
                System.out.println("Uleczanie Postaci "+c);
                System.out.println("...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
        forkJoinPool.submit(() -> System.out.println("done"));

        forkJoinPool.close();
    }
}