import { Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { PokemonsComponent } from './component/pokemons/pokemons.component';
import { TrainersComponent } from './component/trainers/trainers.component';

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
        path: "trainers",
        component: TrainersComponent,
        title: "Trainers"
    },
];
