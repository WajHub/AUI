import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Pokemon } from '../../model/pokemon';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class PokemonService {
  private url = `${environment.apiUrl}/pokemons`;

  constructor(private http: HttpClient) {}

  findAllPokemons(): Observable<Pokemon[]> {
    return this.http.get<Pokemon[]>(this.url);
  }
}
