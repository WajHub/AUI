import { Component } from '@angular/core';
import {PokemonService} from '../../service/pokemon/pokemon.service';
import {ActivatedRoute} from '@angular/router';
import {PokemonDetails} from '../../model/pokemon-details';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-pokemon-details',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './pokemon-details.component.html',
  styleUrl: './pokemon-details.component.css'
})
export class PokemonDetailsComponent {
  public pokemonDetails: PokemonDetails | undefined | null;
  error: string | null = null;

  constructor(private service: PokemonService,
              private route: ActivatedRoute) {
  }

  ngOnInit(){
    this.route.params.subscribe(params => this.getPokemonDetails(params['id']))
  }
  getPokemonDetails(id: string){
    this.service.findPokemonById(id).subscribe({
      next: (details) => {
        this.pokemonDetails = details;
        this.error = null;
      },
      error: (err) => {
        console.error('Error occurred:', err);
        this.pokemonDetails = null;
        this.error = "Pokemon doesn't exist";
      }
    });
  }

}
