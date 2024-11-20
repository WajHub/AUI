import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trainer } from '../../model/trainer';

@Injectable({
  providedIn: 'root'
})
export class TrainerService {
  private url = `${environment.apiUrl}/trainers`;

  constructor(private http:HttpClient) { }

  findAllTrainers(): Observable<Trainer []>{
    return this.http.get<Trainer []>(this.url);
  }
}
