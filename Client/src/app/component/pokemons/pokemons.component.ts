import { Component } from '@angular/core';
import { PokemonService } from '../../service/pokemon/pokemon.service';
import { Pokemon } from '../../model/pokemon';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-pokemons',
  standalone: true,
  templateUrl: './pokemons.component.html',
  styleUrl: './pokemons.component.css',
  providers: [PokemonService]
})
export class PokemonsComponent {
  pokemons: Pokemon[] = []

  constructor(private backendService: PokemonService) {
   }

  ngOnInit(){
    this.getPokemons();
  }
  getPokemons(){
    this.backendService.findAllPokemons().subscribe(data =>{
          this.pokemons = data;
      })
  };
  
}

