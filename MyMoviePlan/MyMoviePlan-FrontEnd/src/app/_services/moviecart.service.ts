import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, Subject, tap, throwError } from 'rxjs';
import { MessengerService } from './messenger.service';

@Injectable({
  providedIn: 'root'
})
export class MoviecartService {

  private _items: any;
  private _seats: any;
  subject = new Subject();
  subject2 = new Subject();
  subject3 = new Subject();

  

  apiUrl: string = 'http://localhost:8080/api/auth/';
  headers = new HttpHeaders().set('Content-Type', 'application/json');


  constructor(
    private httpClient:HttpClient,
    private msg:MessengerService
    
    ) { 

    this._items = JSON.parse(localStorage.getItem('items') ||'[]');
    this._seats = JSON.parse(localStorage.getItem('reservedSeat') ||'[]');
   
  }

  sendItemMsg(item:any) {
       this.subject.next(item) //Triggering an event
  }

  sendSeatMsg(seat:any) {
        this.subject2.next(seat) //Triggering an event
     }

  sendClearSeatMsg(seat:any) {
        this.subject3.next(seat) //Triggering an event
      }
  
  getClearSeatMsg() {
    return this.subject3.asObservable()
  }

  getItemMsg() {
    return this.subject.asObservable()
  }


  getSeatMsg() {
    return this.subject2.asObservable()
  }


  remove(item:any) {
    const index = this._items.indexOf(item);
    this._items.splice(index,1);
    this.syncItems();
  }

  removeSeat(seat:any) { 


    for(let i=0;i<=this._seats.length+1;i++){
      const index = this._seats.indexOf(seat);
      this._seats.splice(index,1);

    }    
    this.syncSeats();
  
    this.msg.sendSeatMsgFromStorageDelete(this._seats);
        
  }


  add(item:any) { 
     this._items.push(item);
     this.syncItems();    
  }

  addSeats(seats:any) { 
    this._seats.push(seats);
    this.syncSeats();

    this.msg.sendSeatMsgFromStorageAdd(seats);
 
 }


  get length() : number{
    return this._items.length
  }

  get lengthSeats() : number{
    return this._seats.length
  }

  get items(){
    return this._items.slice(0)
  }

  get seats(){
   
    return this._seats.slice(0)
  }


  syncItems(){
    localStorage.setItem('items',JSON.stringify(this._items)); // sync the data
  
  }

  syncSeats(){
    localStorage.setItem('reservedSeat',JSON.stringify(this._seats)); // sync the data
    
  }

  saveOrders(orderdata: any): Observable<any> {
    const endpoint = this.apiUrl+'saveOrders';
    return this.httpClient.post(endpoint, orderdata)
    .pipe(
      catchError(this.handleError)
    )
  
  }

  saveSeats(seatsdata: any): Observable<any> {
    const endpoint = this.apiUrl+'saveSeats';
    return this.httpClient.post(endpoint, seatsdata)
    .pipe(
      catchError(this.handleError)
    )
  
  }

  getSeatsData(): Observable<any> {
    const endpoint = this.apiUrl+'listSeats';
    return this.httpClient.get(endpoint)
    .pipe(
     catchError(this.handleError)
    )
    
    }

    getSeatsByShowId(id:any): Observable<any> {
      const endpoint = this.apiUrl+'listSeatsByShowId/'+id;
      return this.httpClient.get(endpoint)
      .pipe(
       catchError(this.handleError)
      )
      
      }

  makePayment(paydata: any): Observable<any> {
    const endpoint = this.apiUrl+'payments';
    console.log(paydata)
    return this.httpClient.post(endpoint, paydata)
    .pipe(
      catchError(this.handleError)
    )
  
  }

  reviewPayment(paymentId1:any, token:any,PayerID1:any): Observable<any> {
       const endpoint = this.apiUrl+'reviewpayment';
    let queryParams  = new HttpParams();
    
    queryParams = queryParams.append("paymentId",paymentId1);
    queryParams = queryParams.append("token",token);
    queryParams = queryParams.append("PayerID",PayerID1);
    return this.httpClient.get(endpoint,{ params:queryParams })
    .pipe(
     catchError(this.handleError)
    )
    
    }
    
  receiptPayment(paymentId1:any, PayerID1:any): Observable<any> {
    const endpoint = this.apiUrl+'receiptpayment';
    let queryParams  = new HttpParams();
    
    queryParams = queryParams.append("paymentId",paymentId1);
    queryParams = queryParams.append("PayerID",PayerID1);
    return this.httpClient.get(endpoint,{ params:queryParams })
    .pipe(
      catchError(this.handleError)
    )
    
 }


    executePayment(paymentId1:any, token:any, PayerID1:any): Observable<any> {
      let queryParams  = new HttpParams();    
      queryParams = queryParams.append("paymentId",paymentId1);      
      queryParams = queryParams.append("token",token);
      queryParams = queryParams.append("PayerID",PayerID1);

      const endpoint = this.apiUrl+'execute_payment';
      return this.httpClient.get(endpoint,{ headers:this.headers,params:queryParams,responseType: 'text'})
      .pipe(
        tap( // Log the result or error
      {
        next: (data) => console.log(data),
        error: (error) => console.log(error)
      }
       //catchError(this.handleError)
      )
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
  console.log(error.error.text);
  return throwError(error.error.text);
};
}
