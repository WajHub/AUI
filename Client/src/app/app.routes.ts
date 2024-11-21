import { Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { PokemonsComponent } from './component/pokemons/pokemons.component';
import { TrainersComponent } from './component/trainers/trainers.component';
import {PokemonDetailsComponent} from './component/pokemon-details/pokemon-details.component';
import {TrainerDetailsComponent} from './component/trainer-details/trainer-details.component';

export const routes: Routes = [
    {
        path: "",
        component: HomeComponent,
        title: "Home Page"
    },
    {
        path: "pokemons",
        component: PokemonsComponent,
        title: "Pokemons"
    },
    {
        path: "pokemons/:id",
        component: PokemonDetailsComponent,
        title:"Pokemon Details"
    },
    {
        path: "trainers",
        component: TrainersComponent,
        title: "Trainers"
    },
    {
      path: "trainers/:id",
      component: TrainerDetailsComponent,
      title:"Trainer Details"
    },
];
