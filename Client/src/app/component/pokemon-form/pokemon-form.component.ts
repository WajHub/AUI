import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {PokemonService} from '../../service/pokemon/pokemon.service';
import {MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle} from '@angular/material/dialog';
import {TrainerService} from '../../service/trainer/trainer.service';
import {Trainer} from '../../model/trainer';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';

@Component({
  selector: 'app-pokemon-form',
  standalone: true,
  imports: [
    FormsModule,
    MatButtonModule,
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    MatDialogTitle,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule
  ],
  templateUrl: './pokemon-form.component.html',
  styleUrl: './pokemon-form.component.css'
})
export class PokemonFormComponent {
  pokemonForm!: FormGroup;
  trainers: Trainer[] = [];

  constructor(private pokemonService: PokemonService,
              private trainerService: TrainerService,
              private formBuilder: FormBuilder,
              private matDialog: MatDialog) {}

  ngOnInit() {
    this.pokemonForm = this.formBuilder.group({
      name: '',
      level: 1,
      trainerId: ''
    })
    this.trainerService.findAllTrainers().subscribe({
      next: (value) => {
        this.trainers = value
        console.log(value);
      }
    })
  }

  onSubmit() {
    if(this.pokemonForm.valid){
      this.pokemonService.createPokemon(this.pokemonForm.value).subscribe(
        {next: response => console.log("Created pokemon!")}
      )
      this.pokemonForm.reset();
      this.matDialog.closeAll();
    }
  }
}
