import { EveningActivity } from './../models/evening-activity';
import { SleepPeriod } from './../models/sleep-period';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SleepService {
  private nights: SleepPeriod [] = []
  private url = environment.baseUrl + 'api/sleep'
  constructor(private http: HttpClient) { }

  calculateElapsedTime(night: SleepPeriod): number{
    let elapsed = 0;
    if(night.start && night.end){
      console.log()
      let timeDiff = night.end?.getDate() - night.start?.getDate();
      console.log(timeDiff);
      let hours = timeDiff/1000/60/60;
      let mins = Math.floor(timeDiff/1000 % 3600);
      elapsed = hours + (Math.round((mins/60)*100))/100;
      console.log(elapsed);
    }

    return elapsed;
  }

  index(): Observable<SleepPeriod[]>{
    return this.http.get<SleepPeriod[]>(this.url ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error retrieving sleep in sleep service' + err)
        );
      })
    );
  }
  findByWorkout(id: number): Observable<SleepPeriod[]>{
    return this.http.get<SleepPeriod[]>(this.url + "/workout/" + id ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error retrieving sleep in sleep service' + err)
        );
      })
    );
  }
  findByActivity(id: number): Observable<SleepPeriod[]>{
    return this.http.get<SleepPeriod[]>(this.url + "/activity/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error retrieving sleep in sleep service' + err)
        );
      })
    );
  }
  findByQuality(low: number = 1, high: number = 10): Observable<SleepPeriod[]>{
    return this.http.get<SleepPeriod[]>(this.url + "/" + low + "/" + high ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error retrieving sleep in sleep service' + err)
        );
      })
    );
  }
  findById(id: number): Observable<SleepPeriod>{
    return this.http.get<SleepPeriod>(this.url + "/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error retrieving sleep in sleep service' + err)
        );
      })
    );
  }
  create(night: SleepPeriod): Observable<SleepPeriod>{

    let testNight = {
      eveningActivity: night.eveningActivity,
      // eveningActivity: {
      //   name: "Read a Book",
      //   id: 1
      // },
      workout: night.workout,
      // workout: {
      //   id: 1,
      //   timeOfDay: "Morning"
      // },
      start: night.start,
      end: night.end,
      exercised: night.exercised,
      largeDinner: night.largeDinner,
      tookNap: night.tookNap,
      hadAlcohol: night.hadAlcohol,
      quality: night.quality
    }
    console.log(testNight)
    return this.http.post<SleepPeriod>(this.url, testNight ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error creating sleep in sleep service ' + err)
        );
      })
    );
  }
  update(night: SleepPeriod): Observable<SleepPeriod>{
    return this.http.put<SleepPeriod>(this.url + "/" + night.id, night ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error updating sleep in sleep service ' + err)
        );
      })
    );
  }
  delete(id: number): Observable<SleepPeriod>{
    return this.http.delete<SleepPeriod>(this.url + "/" + id ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error deleting sleep in sleep service ' + err)
        );
      })
    );
  }

}
