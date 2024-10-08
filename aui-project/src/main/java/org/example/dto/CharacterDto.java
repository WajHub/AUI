package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.model.Profession;

@Getter
@Setter
@ToString
public class CharacterDto {
    String name;
    int level;
    String profession;
}
