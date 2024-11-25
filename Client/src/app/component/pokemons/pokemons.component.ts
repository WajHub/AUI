import { Component } from '@angular/core';
import { PokemonService } from '../../service/pokemon/pokemon.service';
import { Pokemon } from '../../model/pokemon';
import { NgFor } from '@angular/common';
import {RouterLink} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {TrainerFormComponent} from '../trainer-form/trainer-form.component';
import {PokemonFormComponent} from '../pokemon-form/pokemon-form.component';
import {TrainerFormEditComponent} from '../trainer-form-edit/trainer-form-edit.component';
import {PokemonFormEditComponent} from '../pokemon-form-edit/pokemon-form-edit.component';

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

  constructor(private service: PokemonService,
              private dialog: MatDialog,
              private dialogEdit: MatDialog) {
   }

  ngOnInit(){
    this.getPokemons();
  }
  getPokemons(){
    this.service.findAllPokemons().subscribe(data =>{
          this.pokemons = data;
      })
  };

  openDialog() {
    const dialogRef = this.dialog.open(PokemonFormComponent,{
      hasBackdrop: true
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed, result:', result);
      setTimeout(() => {
        this.getPokemons();
      }, 300);
    });
  }

  openDialogEdit(id: string) {
    const dialogRef = this.dialogEdit.open(PokemonFormEditComponent,{
      hasBackdrop: true
    });
    dialogRef.componentInstance.pokemonId = id;
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed, result:', result);
      setTimeout(() => {
        this.getPokemons();
      }, 300);
    });
  }

  deleteTrainer(id:string) {
    this.service.deletePokemon(id).subscribe(result =>{
      console.log('Dialog closed, result:', result);
      setTimeout(() => {
        this.getPokemons();
      }, 300);
    });
  }
}

