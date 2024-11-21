import { Component } from '@angular/core';
import { PokemonService } from '../../service/pokemon/pokemon.service';
import { Pokemon } from '../../model/pokemon';
import { NgFor } from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-pokemons',
  standalone: true,
  templateUrl: './pokemons.component.html',
  styleUrl: './pokemons.component.css',
  providers: [PokemonService],
  imports: [RouterLink]
})
export class PokemonsComponent {
  pokemons: Pokemon[] = []

  constructor(private service: PokemonService) {
   }

  ngOnInit(){
    this.getPokemons();
  }
  getPokemons(){
    this.service.findAllPokemons().subscribe(data =>{
          this.pokemons = data;
      })
  };

}

