package com.PokemonService.dto.request;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class PutPokemonDtoRequest {
    private String name;

    private int level;
    private UUID trainer;

    public boolean isValid(){
        return !name.isEmpty() && level > 0;
    }
}

