import { Component } from '@angular/core';
import {TrainerService} from '../../service/trainer/trainer.service';
import {PokemonService} from '../../service/pokemon/pokemon.service';
import {Trainer} from '../../model/trainer';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {NgIf} from '@angular/common';
import {TrainerDetails} from '../../model/trainer-details';

@Component({
  selector: 'app-trainer-details',
  standalone: true,
  imports: [
    RouterLink,
    NgIf
  ],
  templateUrl: './trainer-details.component.html',
  styleUrl: './trainer-details.component.css'
})
export class TrainerDetailsComponent {
  public trainer: TrainerDetails | undefined | null;
  public errorMessage: string | null = null;

  constructor(private serviceTrainer: TrainerService,
              private servicePokemon: PokemonService,
              private route: ActivatedRoute) {
  }

  ngOnInit(){
    this.route.params.subscribe(params => {
    this.getTrainerDetails(params['id']);
    })
  }

  getTrainerDetails(id: string) {
    this.serviceTrainer.findTrainerById(id)
      .subscribe({
        next: (trainerDetails)=> {
          this.trainer = trainerDetails;
          this.getPokemonsByTrainer(trainerDetails.id);
          this.errorMessage = null;
        },
        error: (er) => {
          this.trainer = null;
          this.errorMessage = "Trainer doesn't exist!";
        }
      })
  }

  getPokemonsByTrainer(id:string) {
    this.servicePokemon.findPokemonsByTrainer(id)
      .subscribe({
        next: (pokemons)=>{
          if(this.trainer)  this.trainer.pokemons=pokemons;
        },
        error: (er) =>{
          this.errorMessage = "Error fetching pokemons";
        }
      })
  }

}
