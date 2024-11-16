package com.PokemonService.dto.request;

import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdatePokemonLevelRequest {
    int level;

    public boolean isValid(){
        return level > 0;
    }
}
