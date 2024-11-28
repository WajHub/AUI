import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {catchError, delay, Observable, of, tap, throwError} from 'rxjs';
import { Pokemon } from '../../model/pokemon';
import { environment } from '../../../environments/environment';
import {PokemonDetails} from '../../model/pokemon-details';
import {Trainer} from '../../model/trainer';

@Injectable({
  providedIn: 'root',
})
export class PokemonService {
  private url = `${environment.apiUrl}`;

  constructor(private http: HttpClient) {}

  findAllPokemons(): Observable<Pokemon[]> {
    return this.http.get<Pokemon[]>(this.url+"/pokemons");
  }

  findPokemonById(id:string): Observable<PokemonDetails>{
    return this.http.get<PokemonDetails>(this.url+"/pokemons/"+id).pipe(
      delay(1500),
      catchError(err => {
        console.error('Error fetching Pokemon details:', err);
        return throwError(() => err); // Propagate the error to the subscriber
      })
    );
  }
  findPokemonsByTrainer(id:string): Observable<Pokemon []> {
    return this.http.get<Pokemon []>(this.url+"/trainers/"+id+"/pokemons").pipe(
      catchError(err => {
        console.error('Error fetching Pokemons:', err);
        return throwError(() => err); // Propagate the error to the subscriber
      })
    );
  }
  createPokemon(pokemon:PokemonDetails):Observable<PokemonDetails>{
    return this.http.put<PokemonDetails>(`${this.url}/pokemons`, {name:pokemon.name, level:pokemon.level, trainer: pokemon.trainerId}).pipe(
      catchError(err => {
        console.error('Error fetching Pokemons:', err);
        return throwError(() => err); // Propagate the error to the subscriber
      })
    )
  }

  deletePokemon(id: string):Observable<any> {
    return this.http.delete<any>(`${this.url}/pokemons/${id}`);
  }

  updatePokemonLevel(pokemonId:string, level:number) {
    return this.http.patch<PokemonDetails>(`${this.url}/pokemons/${pokemonId}`, {level: level}).pipe(
      tap(response => console.log(response)),
      catchError(err => {
        return throwError(() =>err)
      })
    );
  }
}
