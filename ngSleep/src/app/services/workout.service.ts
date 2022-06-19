import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Workout } from '../models/workout';

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
  private workoutTimes: Workout [] = []
  private url = environment.baseUrl + 'api/workouts'

  constructor(private http: HttpClient) { }

  index(): Observable<Workout[]>{
    return this.http.get<Workout[]>(this.url ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error retrieving workouts in workout service ' + err)
        );
      })
    );
  }

}
