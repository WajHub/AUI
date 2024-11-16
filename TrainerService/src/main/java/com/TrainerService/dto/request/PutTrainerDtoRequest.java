package com.TrainerService.dto.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PutTrainerDtoRequest {
    String name;
    int age;

    public boolean isValid(){
        return (!name.isEmpty() && age > 0);
    }
}
