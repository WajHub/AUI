import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PokemonsComponent } from "./component/pokemons/pokemons.component";
import { TrainersComponent } from "./component/trainers/trainers.component";
import { HomeComponent } from "./component/home/home.component";
import { NavComponent } from "./component/nav/nav.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, PokemonsComponent, TrainersComponent, HomeComponent, NavComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
}
