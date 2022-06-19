import { EveningActivity } from './../models/evening-activity';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {
  private activities: EveningActivity [] = []
  private url = environment.baseUrl + 'api/activities'

  constructor(private http: HttpClient) { }


  index(): Observable<EveningActivity[]>{
    return this.http.get<EveningActivity[]>(this.url ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error retrieving activities in activity service: ' + err)
        );
      })
    );
  }
  create(ea:EveningActivity): Observable<EveningActivity>{
    return this.http.post<EveningActivity>(this.url, ea).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('error creating activity in activity service: ' + err)
        );
      })
    );
  }
}
