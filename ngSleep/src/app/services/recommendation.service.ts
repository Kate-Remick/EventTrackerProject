import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {
  private url = environment.baseUrl + 'api/recommendations'
  constructor(private http: HttpClient) { }

  index(): Observable<string[]>{
    return this.http.get<string[]>(this.url + "/all" ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('todoService.index(): error retrieving todo list: ' + err)
        );
      })
    );
  }

}
