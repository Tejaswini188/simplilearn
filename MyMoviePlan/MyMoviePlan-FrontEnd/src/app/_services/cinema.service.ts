import { HttpClient, HttpErrorResponse, HttpEvent, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  apiUrl: string = 'http://localhost:8080/api/auth/';
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private httpClient:HttpClient) { }

  
  // Returns an observable

  /*File Upload*/ 
  upload2(file: File, filename: string): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file,filename);
    const req = new HttpRequest('POST', `${this.apiUrl}upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.httpClient.request(req);
  }

  upload(file: File, filename: string): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file,filename);
    const endpoint = this.apiUrl+'upload';
    /*const req = new HttpRequest('POST', `${this.apiUrl}upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });*/
    return this.httpClient.post(endpoint, formData).pipe(
      catchError(this.handleError)
    )
  }
  getFiles(): Observable<any> {
    return this.httpClient.get(`${this.apiUrl}downloadfiles`);
  }

  getImage(imagename:String): Observable<any>{

    return this.httpClient.get(`${this.apiUrl}getImage/${imagename}`);
  }

  getImageById(imageid:String): Observable<any>{

    return this.httpClient.get(`${this.apiUrl}getImageById/${imageid}`);
  }

  getImages(): Observable<any>{

    return this.httpClient.get(`${this.apiUrl}getImages`);
  }

  /* Genre Functions*/

  saveGenre(genredata: any): Observable<any> {
    const endpoint = this.apiUrl+'saveGenre';
    return this.httpClient.post(endpoint, genredata)
    .pipe(
      catchError(this.handleError)
    )

 }

 deleteGenre(GenreId: number): Observable<any> {
   const endpoint = this.apiUrl+'deleteGenre/'+GenreId;
   return this.httpClient.delete(endpoint)
   .pipe(
     catchError(this.handleError)
   )
   //return this.http.delete(this.baseUrl + '/website/' + website.id, options);
}

updateGenre(gendreData: any): Observable<any> {
 const endpoint = this.apiUrl+'updateGenre/'+gendreData.id;
 return this.httpClient.put(endpoint, gendreData,{ headers: this.headers })
 .pipe(
   catchError(this.handleError)
 )

}
updateMovieStatus(statusN: number, idN:number): Observable<any> {
  const setparams = new HttpParams().set('status',statusN).set('id',idN);
  const endpoint = this.apiUrl+'updateMovieStatus';
  return this.httpClient.put(endpoint,'',{ headers: this.headers, params:setparams })
  .pipe(
    catchError(this.handleError)
  )

}

listGenreData(): Observable<any> {
 const endpoint = this.apiUrl+'listGenre';
 return this.httpClient.get(endpoint)
 .pipe(
   catchError(this.handleError)
 )

}

getGenre(GenreId:number): Observable<any> {
 const endpoint = this.apiUrl+'getGenre/'+ GenreId;
 return this.httpClient.get(endpoint)
 .pipe(
   catchError(this.handleError)
 )

}

getGenreByName(productname:string): Observable<any> {
 const endpoint = this.apiUrl+'getGenreName/'+ productname;
 return this.httpClient.get(endpoint)
 .pipe(
   catchError(this.handleError)
 )

}

/* Movie Functions*/
saveMovie(moviedata: any): Observable<any> {
  const endpoint = this.apiUrl+'saveMovie';
  return this.httpClient.post(endpoint, moviedata)
  .pipe(
    catchError(this.handleError)
  )

}

deleteMovie(MovieId: number): Observable<any> {
 const endpoint = this.apiUrl+'deleteMovie/'+MovieId;
 return this.httpClient.delete(endpoint)
 .pipe(
   catchError(this.handleError)
 )

}

updateMovie(movieData: any, id: number): Observable<any> {
const endpoint = this.apiUrl+'updateMovie/'+id;

return this.httpClient.put(endpoint, movieData,{ headers: this.headers })
.pipe(
 catchError(this.handleError)
)

}

listMovieData(): Observable<any> {
const endpoint = this.apiUrl+'listMovies';
return this.httpClient.get(endpoint)
.pipe(
 catchError(this.handleError)
)

}

listEnabledMovies(): Observable<any> {
  const endpoint = this.apiUrl+'listEnabledMovies';
  return this.httpClient.get(endpoint)
  .pipe(
   catchError(this.handleError)
  )
  
  }

getMovie(MovieId:number): Observable<any> {
const endpoint = this.apiUrl+'getMovie/'+ MovieId;
return this.httpClient.get(endpoint)
.pipe(
 catchError(this.handleError)
)

}

getMoviesByName(movieName:string): Observable<any> {
const endpoint = this.apiUrl+'getMoviesName/'+ movieName;
return this.httpClient.get(endpoint)
.pipe(
 catchError(this.handleError)
)

}

getMoviesByLanguage(movieName:string): Observable<any> {
  const endpoint = this.apiUrl+'getMoviesLanguage/'+ movieName;
  return this.httpClient.get(endpoint)
  .pipe(
   catchError(this.handleError)
  )
  
  }


getMoviesByGenre(movieName:string): Observable<any> {
  const endpoint = this.apiUrl+'getMoviesByGenre/'+ movieName;
  return this.httpClient.get(endpoint)
  .pipe(
   catchError(this.handleError)
  )
  
  }



/* Show Functions*/

saveShow(showdata: any): Observable<any> {
  const endpoint = this.apiUrl+'saveShow';
  return this.httpClient.post(endpoint, showdata)
  .pipe(
    catchError(this.handleError)
  )

}

deleteShow(showId: number): Observable<any> {
 const endpoint = this.apiUrl+'deleteShow/'+showId;
 return this.httpClient.delete(endpoint)
 .pipe(
   catchError(this.handleError)
 )
 //return this.http.delete(this.baseUrl + '/website/' + website.id, options);
}

updateShow(showData: any,id: any): Observable<any> {
const endpoint = this.apiUrl+'updateShow/'+id;
return this.httpClient.put(endpoint, showData,{ headers: this.headers })
.pipe(
 catchError(this.handleError)
)

}

listShowData(): Observable<any> {
const endpoint = this.apiUrl+'listShow';
return this.httpClient.get(endpoint)
.pipe(
 catchError(this.handleError)
)

}

getShowsByMovie(moviename: string): Observable<any> {
  const endpoint = this.apiUrl+'getShowsByMovie/'+ moviename;
  return this.httpClient.get(endpoint)
  .pipe(
   catchError(this.handleError)
  )
}

getShow(showId:number): Observable<any> {
const endpoint = this.apiUrl+'getShow/'+ showId;
return this.httpClient.get(endpoint)
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
