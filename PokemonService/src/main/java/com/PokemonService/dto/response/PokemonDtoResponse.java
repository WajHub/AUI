package com.PokemonService.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class PokemonDtoResponse {
    UUID id;

     String name;
     int level;

     UUID trainerId;
}
