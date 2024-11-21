import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import { Trainer } from '../../model/trainer';
import {TrainerDetails} from '../../model/trainer-details';

@Injectable({
  providedIn: 'root'
})
export class TrainerService {
  private url = `${environment.apiUrl}/trainers`;

  constructor(private http:HttpClient) { }

  findAllTrainers(): Observable<Trainer []>{
    return this.http.get<Trainer []>(this.url);
  }
  findTrainerById(id: string):Observable<TrainerDetails>{
    return this.http.get<TrainerDetails>(this.url+"/"+id).pipe(
      catchError(err =>{
        return throwError(() =>err)
      })
    );
  }
}
