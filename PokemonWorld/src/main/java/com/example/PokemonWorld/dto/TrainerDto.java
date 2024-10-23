package com.example.PokemonWorld.dto;

import lombok.*;
import org.example.dto.PokemonDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class TrainerDto {
    private UUID id;
    private String name;
    private int age;
    private List<PokemonDto> pokemonDtoList;
}
