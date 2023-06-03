import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDetails } from '../_models/user.model';
import { AlertService } from '../_services/alert.service';
import { AuthenticationService } from '../_services/authentication.service';

@Component({
  selector: 'app-moviecart',
  templateUrl: './moviecart.component.html',
  styleUrls: ['./moviecart.component.css']
})
export class MoviecartComponent implements OnInit {


  currentUser: UserDetails;
  reserved:string[] = ['A2', 'A3', 'C5', 'C6', 'C7', 'C8', 'J1', 'J2', 'J3', 'J4'];
  selected:any = [];
  available:string[]=[];
  reservedStatus:string='reserved';
  seatStatus:string='selected';
  rows:string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'];
 // cols:number[] = [1, 2, 3, 4, 5, 6, 7, 8];
 cols:number[]=[];
//borrowed
 setNewClass: any;
 public settings: any;
 public rowLength: number;
 private reservedSeat: any[] = [];
 private selectingSeat: any[] = [];

  constructor(
     private route: Router,
     private authenticationService: AuthenticationService,
     private alertService: AlertService
      ) { 

        this.currentUser = this.authenticationService.currentUserValue;

         this.seatStorage();
  }

  ngOnInit(): void {

    //this.seatSelected('A2');
    //this.getStatus('A2');
    //this.showSelected();
    //this.availableSeats(80);

    //this.generateCollumn(10);
  }

  availableSeats(seats:number){

    this.generateCollumn(seats)
      
    for(let i=0;i<this.rows.length;i++){

      for(let j=0;j<this.cols.length;j++){
        
        if(this.reserved.indexOf(this.rows[i]+this.cols[j]) > -1) {
          //this.seatStatus='reserved';
          continue;
      }
        
        this.available.push(this.rows[i]+this.cols[j]);

      }
    }

    console.log(this.available);

  }

  generateCollumn(seats:number){

    let seat=Math.floor(seats/10);

    for(let i=1;i<=seat;i++){

      this.cols.push(i);
    }
    console.log(this.cols);
  }

  seatSelected(seatPos: string){

    console.log("Selected Seat: " + seatPos);
    let index = this.selected.indexOf(seatPos);
    console.log(index);
    if(index != -1) {
      // seat already selected, remove
      this.selected.splice(index, 1)
  } else {
      // new seat, push
      this.selected.push(seatPos);
  }    
  console.log(this.selected);
  }

  getStatus(seatPos: string){

    //"noImplicitReturns": false,

    if(this.reserved.indexOf(seatPos) > -1) {
      this.seatStatus='reserved'
      return 'reserved';
  } else if(this.selected.indexOf(seatPos) > -1) {
    this.seatStatus='selected';
    return 'selected';
  }

  console.log(this.seatStatus);

  }

  clearSelected () {
    this.selected = [];
   }

   showSelected() {
    if(this.selected.length > 0) {
        //alert("Selected Seats: \n" + this.selected);
        console.log("Selected Seats: \n" + this.selected);
    } else {
        //alert("No seats selected!");
        console.log("No seats selected!");
    }
}


seatStorage(){

  this.settings = {
    rows: [1, 1, 1, 1, 1,1, 1, 1, 1, 1,],
    cols: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    rowCssPrefix: 'row-',
    colCssPrefix: 'col-',
    seatWidth: 10,
    seatHeight: 10,
    seatCss: 'seat',
    selectedSeatCss: 'selectedSeat',
    selectingSeatCss: 'selectingSeat'
  };
  console.log("Getting settings", localStorage.getItem("settings"));
  let ls_settings = localStorage.getItem("settings");
  console.log(ls_settings);

  if (ls_settings == null || ls_settings == "undefined") {
    console.log("COMing INSIDE");
    localStorage.setItem("settings", JSON.stringify(this.settings));
  } else {
    if (typeof ls_settings === 'string') {
      this.settings = JSON.parse(localStorage.getItem("settings")!);
    }
  }

  if (!localStorage.getItem("reservedSeat")) {
    localStorage.setItem("reservedSeat", JSON.stringify(this.reservedSeat));
  } else {
    this.reservedSeat = JSON.parse(localStorage.getItem("reservedSeat")!);
  }

  // if (!localStorage.getItem("reservedSeat")) {
  //   localStorage.setItem("reservedSeat", JSON.stringify(this.reservedSeat));
  // } else {
  //   this.reservedSeat = JSON.parse(localStorage.getItem("reservedSeat"));
  // }

  this.rowLength = this.settings.rows.length;
}


clearSeats(){
  localStorage.removeItem('settings');
}
clearReservedSeats(){
  localStorage.removeItem('reservedSeat');
}


getSeatNo(i: number, j: number) {
  //console.log(i + j * this.rowLength + 1);
  return (i + j * this.rowLength + 1);
}

getClassName(i: number, j: number) {
  let id = this.settings.rowCssPrefix + i.toString() + ' ' + this.settings.colCssPrefix + j.toString();
  let className = this.settings.seatCss + ' ' + id;
  if (this.reservedSeat.length > 0 && this.reservedSeat.indexOf(this.getSeatNo(i, j)) != -1) {
    className += (' ' + this.settings.selectedSeatCss);
  }

  return className;
}

seatClick(seatno: any, event: any) {
  if (event.target.className.indexOf('selectedSeat') !== -1) {
    return alert('Already booked! :(');
  }

  if(this.selectingSeat.length+1>9){
    return alert('You cannot have more seats than the number of tickets selected');
  }else{
  if (event.target.className.indexOf('selectingSeat') == -1) {
    event.target.className += ' selectingSeat';
    this.selectingSeat.push(seatno);
  } else {
    event.target.className.replace('selectingSeat', '');
    let index = this.selectingSeat.indexOf(seatno);
    if (index > -1) {
      this.selectingSeat.splice(index, 1);
    }
  
  }
}

}

getTop(i: number) {
  return i * this.settings.seatHeight;
}

getLeft(j: number) {
  return j * this.settings.seatWidth;
}

bookTicket() {
  if (this.selectingSeat.length == 0) {
    alert('Please select seat..!');
    return;
  }
  this.reservedSeat = this.reservedSeat.concat(this.selectingSeat);
  localStorage.setItem("reservedSeat", JSON.stringify(this.reservedSeat));
  alert('You have booked your ticket successfully');

  this.route.navigate(['/']);
}
  

}
