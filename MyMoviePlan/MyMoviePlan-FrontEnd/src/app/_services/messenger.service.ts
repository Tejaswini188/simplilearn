import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Show } from '../_models/show';

@Injectable({
  providedIn: 'root'
})
export class MessengerService {
  subject = new Subject();
  subject2 = new Subject();
  subject3 = new Subject();
  subject4 = new Subject();

  constructor() { }

  sendMsg(show:Show) {
    //console.log(show);
    this.subject.next(show) //Triggering an event
  }

  sendSeatMsg(seat:any) {
    //console.log(show);
    this.subject2.next(seat) //Triggering an event
  }

  getMsg() {
    return this.subject.asObservable()
  }

  getSeatMsg() {
    return this.subject2.asObservable()
  }

  sendSeatMsgFromStorageAdd(seat:any) {
    //console.log(show);
    this.subject3.next(seat) //Triggering an event
  }

  getSeatMsgFromStorageAdd() {
    return this.subject3.asObservable()
  }

  sendSeatMsgFromStorageDelete(seat:any) {
    //console.log(show);
    this.subject3.next(seat) //Triggering an event
  }

  getSeatMsgFromStorageDelete() {
    return this.subject3.asObservable()
  }

}
