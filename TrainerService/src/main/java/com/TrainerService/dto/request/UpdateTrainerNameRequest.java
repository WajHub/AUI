package com.TrainerService.dto.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateTrainerNameRequest {
    String name;

    public String getName(){
        return this.name;
    }

    public boolean isValid(){
        return !name.isEmpty();
    }
}
