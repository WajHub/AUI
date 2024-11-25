import { Component } from '@angular/core';
import {Pokemon} from '../../model/pokemon';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {TrainerService} from '../../service/trainer/trainer.service';
import {MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle} from '@angular/material/dialog';
import {PokemonService} from '../../service/pokemon/pokemon.service';
import {PokemonDetails} from '../../model/pokemon-details';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-pokemon-form-edit',
  standalone: true,
  imports: [
    FormsModule,
    MatButtonModule,
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    MatDialogTitle,
    ReactiveFormsModule
  ],
  templateUrl: './pokemon-form-edit.component.html',
  styleUrl: './pokemon-form-edit.component.css'
})
export class PokemonFormEditComponent {
  pokemonId: string | undefined;
  pokemon: PokemonDetails | undefined | null;
  pokemonForm!: FormGroup;

  constructor(private servicePokemon: PokemonService,
              private formBuilder: FormBuilder,
              private matDialog: MatDialog) {
    this.pokemonForm = formBuilder.group({name:["", Validators.required], level:[1, [Validators.required, Validators.min(0)]]});
  }

  ngOnInit(){
    if(this.pokemonId) {
      this.getPokemonDetails(this.pokemonId)
    }
  }

  getPokemonDetails(id: string) {
    this.servicePokemon.findPokemonById(id)
      .subscribe({
        next: (pokemonDetails)=> {
          this.pokemon = pokemonDetails;
          this.setForm();
        },
        error: (er) => {
          this.pokemon = null;
        }
      })
  }

  setForm(){
    this.pokemonForm = this.formBuilder.group({
      name: [this.pokemon?.name, Validators.required],
      level: [this.pokemon?.level, [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit(){
    if (this.pokemonForm?.valid && this.pokemonId) {
      this.servicePokemon.updatePokemonLevel(this.pokemonId, this.pokemonForm.value.level).subscribe({
        next: response => console.log('Trainer created response:', response),
        error: err => console.error('Error updating pokemon:', err)
      });
      console.warn('Pokemon form submitted:', this.pokemonForm.value);
      this.pokemonForm.reset();
      this.matDialog.closeAll();
    } else {
      console.warn('Form is invalid');
    }
  }

}
