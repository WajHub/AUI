package com.example.PokemonWorld.dto.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PutPokemonDtoRequest {
    private String name;

    private int level;

    public boolean isValid(){
        return !name.isEmpty() && level > 0;
    }
}

