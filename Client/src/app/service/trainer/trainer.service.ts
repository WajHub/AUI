import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import {catchError, Observable, tap, throwError} from 'rxjs';
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
  createTrainer(trainer: Trainer):Observable<Trainer> {
    return this.http.put<Trainer>(this.url, trainer).pipe(
      tap(response => console.log('Trainer created successfully:', response)),
      catchError(err =>{
        return throwError(() =>err)
      }));
  }
  updateTrainer(trainerId:string, name:string) {
    return this.http.patch<Trainer>(`${this.url}/${trainerId}`, {name: name}).pipe(
      tap(response => console.log(response)),
      catchError(err => {
        return throwError(() =>err)
      })
    );
  }

  deleteTrainer(id: string):Observable<any> {
    return this.http.delete<any>(`${this.url}/${id}`);
  }
}
