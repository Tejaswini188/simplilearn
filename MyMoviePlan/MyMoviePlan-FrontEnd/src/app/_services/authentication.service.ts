import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, config, map, Observable, throwError } from 'rxjs';
import { User, UserDetails } from '../_models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<UserDetails>;
    public currentUser: Observable<UserDetails>;
    apiUrl: string = 'http://localhost:8080/api/auth/';
    headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<UserDetails>(JSON.parse(localStorage.getItem('currentUser')!));
    this.currentUser = this.currentUserSubject.asObservable();
   }

   public get currentUserValue(): UserDetails {
    return this.currentUserSubject.value;
}

login(username: any, password: any) {
  return this.http.post<any>(`apiUrl/users/authenticate`, { username, password })
      .pipe(map(user => {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
          return user;
      }));
}
login2(login: any): Observable<any> {
  const endpoint = this.apiUrl+'signin';
  //console.log(endpoint);
  //console.log(login);
  return this.http.post<any>(endpoint, login)
    .pipe(
      map((userdetails: UserDetails) => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(userdetails));
        this.currentUserSubject.next(userdetails);
        //console.log(userdetails);
        return userdetails;
    }),
      catchError(this.handleError)
    );
}


signUp(signup: any): Observable<any> {
  const endpoint = this.apiUrl+'signin';
  //console.log(endpoint);
  //console.log(login);
  return this.http.post<any>(endpoint, signup)
    .pipe(
          catchError(this.handleError)
    );
}

resetPassword(userdata: any): Observable<any> {
  const endpoint = this.apiUrl+'resetPass';
  return this.http.put(endpoint, userdata,{ headers: this.headers })
  .pipe(x=>{
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null!);
    return x;
  },    
    catchError(this.handleError)
  )
}

logout1() {
  // remove user from local storage and set current user to null
  localStorage.removeItem('currentUser');
  this.currentUserSubject.next(null!);
}
logout(): Observable<any>  {
  // remove user from local storage and set current user to null
  const endpoint = this.apiUrl+'signout';

  return this.http.post<any>(endpoint,null )
  .pipe(
    x=>{
      localStorage.removeItem('currentUser');
      localStorage.removeItem('items');
      localStorage.removeItem('reservedSeat');
      this.currentUserSubject.next(null!);
      return x;
    },
    catchError(this.handleError)
  );


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
