import { Component } from '@angular/core';
import { TrainerService } from '../../service/trainer/trainer.service';
import { Trainer } from '../../model/trainer';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-trainers',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './trainers.component.html',
  styleUrl: './trainers.component.css',
  providers: [TrainerService]
})
export class TrainersComponent {

  trainers: Trainer [] = [];

  constructor(private service: TrainerService){}

  ngOnInit(){
    this.findAllTrainers();
  }

  findAllTrainers(){
    return this.service.findAllTrainers().subscribe(data =>{
      this.trainers = data;
    });
  }

}
