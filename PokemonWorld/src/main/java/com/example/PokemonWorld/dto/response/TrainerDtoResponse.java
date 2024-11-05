package com.example.PokemonWorld.dto.response;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class TrainerDtoResponse {
    private UUID id;
    private String name;
    private int age;
    private List<PokemonDtoResponse> pokemonDtoList;
}
