import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NavComponent } from "./component/nav/nav.component";
import { PokemonService } from './service/pokemon/pokemon.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { provideHttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, NavComponent],
  providers: [HttpClient],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
}
