import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { User } from '../_models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl: string = 'http://localhost:8080/api/auth/';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  results:any;

  constructor(private http: HttpClient) { }

register(signup: any): Observable<any> {
  const endpoint = this.apiUrl+'signup';

  return this.http.post<any>(endpoint, signup)
    .pipe(
          catchError(this.handleError)
    );
}

  delete(id: number) {
      return this.http.delete(`apiUrl/users/${id}`);
  }


  getUserData(userId:number): Observable<any> {
    const endpoint = this.apiUrl+'userDetails/'+ userId;
    return this.http.get(endpoint)
    .pipe(
     catchError(this.handleError)
    )
    
    }

    getUsers(): Observable<any> {
      const endpoint = this.apiUrl+'userDetails';
      return this.http.get(endpoint)
      .pipe(
       catchError(this.handleError)
      )
      
      }

      getOrders(): Observable<any> {
        const endpoint = this.apiUrl+'listOrders';
        return this.http.get(endpoint)
        .pipe(
         catchError(this.handleError)
        )        
        }

        getOrdersById(id:any): Observable<any> {
          const endpoint = this.apiUrl+'getOrderDetails/'+id;
          return this.http.get(endpoint)
          .pipe(
           catchError(this.handleError)
          )        
          }


          getPayDetails(): Observable<any> {
            const endpoint = this.apiUrl+'listPayDetails';
            return this.http.get(endpoint)
            .pipe(
             catchError(this.handleError)
            )        
            }

            getPayDetailsByOrderID(id:any): Observable<any> {
              const endpoint = this.apiUrl+'getPayDetails/'+id;
              return this.http.get(endpoint)
              .pipe(
               /* map(i => this.results=i)
                ,*/
               catchError(this.handleError)
              )        
              }

              getAllUsers(): Observable<any> {
                const endpoint = this.apiUrl+'allUsers';
                return this.http.get(endpoint)
                .pipe(
                 catchError(this.handleError)
                )        
                }
  



   // Handle API errors
 handleError(error: HttpErrorResponse) {
  if (error.error instanceof ErrorEvent) {
    console.error('An error occurred:', error.error.message);
  } else {
    console.error(
      `Backend returned code ${error.status}, ` +
      `body was: ${error.error}`);
  }
  console.log(error);
  return throwError(error);
};
}
