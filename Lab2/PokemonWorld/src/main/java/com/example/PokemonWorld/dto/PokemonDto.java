package org.example.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class PokemonDto {

    private UUID id;

    private String name;

    private int level;

    private String trainerName;

}
