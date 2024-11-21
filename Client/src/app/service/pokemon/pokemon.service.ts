import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {catchError, delay, Observable, of, throwError} from 'rxjs';
import { Pokemon } from '../../model/pokemon';
import { environment } from '../../../environments/environment.development';
import {PokemonDetails} from '../../model/pokemon-details';

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
}
