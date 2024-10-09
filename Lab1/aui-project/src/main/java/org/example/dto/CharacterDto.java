package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.model.Profession;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CharacterDto {
    String name;
    int level;
    String profession;
}
