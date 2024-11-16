package com.TrainerService.init;


import com.TrainerService.mapper.Mapper;
import com.TrainerService.model.Trainer;
import com.TrainerService.repository.TrainerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    private TrainerRepository trainerRepository;
    private Mapper mapper;

    @Autowired
    public DataInitializer(TrainerRepository trainerRepository, Mapper mapper) {
        this.trainerRepository = trainerRepository;
        this.mapper = mapper;
    }
    @PostConstruct
    private void initData(){
        System.out.println("INITIALIZING DATA");
        List<Trainer> trainers = Stream.of(
                Trainer.builder()
                        .id(UUID.fromString("b6bbff21-71c4-492f-af34-edaafb16d3e4"))
                        .name("Ash")
                        .age(10)
                        .build(),
                Trainer.builder()
                        .id(UUID.fromString("3b25be7c-a2ba-4240-8723-969fb130fa22"))
                        .name("Misty")
                        .age(12)
                        .build()
        ).toList();

        trainers.forEach((trainer) -> trainerRepository.save(trainer));


    }

}
