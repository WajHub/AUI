package org.example;
import org.example.dto.CharacterDto;
import org.example.model.Profession;
import org.example.model.Character;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        List<Profession> professions  = Stream.of(
                Profession.builder()
                        .name("Wojownik")
                        .baseArmor(50)
                        .characters(new ArrayList<>())
                        .build(),
                Profession.builder()
                        .name("Mag")
                        .baseArmor(20)
                        .characters(new ArrayList<>())
                        .build()
                ).toList();

        List<Character> characters = Stream.of(
                Character.builder()
                        .name("Hubert")
                        .level(20)
                        .profession(Profession.getByName(professions, "Wojownik"))
                        .build(),
                Character.builder()
                        .name("Postac 2 ")
                        .level(50)
                        .profession(Profession.getByName(professions, "Wojownik"))
                        .build(),
                Character.builder()
                        .name("Postac 3")
                        .level(10)
                        .profession(Profession.getByName(professions, "Mag"))
                        .build(),
                Character.builder()
                        .name("AA")
                        .level(19)
                        .profession(Profession.getByName(professions, "Wojownik"))
                        .build()
        ).toList();


       professions.forEach(p -> p.getCharacters()
               .addAll(characters.stream()
                   .filter(c -> c.getProfession().getName().equals(p.getName()))
                   .toList())
                );


        System.out.println("Zadanie 2");
        professions.forEach(prof -> {
            System.out.println(prof);
            prof.getCharacters().forEach(System.out::println);
        });
        System.out.println("--------------------");

        System.out.println("Zadanie 3");
        Set<Character> charactersSet = professions.stream()
                .flatMap(prof -> prof.getCharacters().stream()).collect(Collectors.toSet());
        charactersSet.forEach(System.out::println);
        System.out.println("--------------------");

        System.out.println("Zadanie 4");
        charactersSet.stream().filter(c -> c.getLevel()> 15).sorted(Comparator.comparing(Character::getName)).forEach(System.out::println);
        System.out.println("--------------------");

        System.out.println("Zadanie 5");
        List<CharacterDto> characterDtos = charactersSet.stream().map(c -> new CharacterDto(c.getName(), c.getLevel(),c.getProfession().getName())).toList();
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
            charactersSet.parallelStream().forEach((c) -> {
                System.out.println("Uleczanie Postaci "+c);
                System.out.println("...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
        forkJoinPool.submit(() -> System.out.println("Done!"));

        forkJoinPool.close();
    }
}