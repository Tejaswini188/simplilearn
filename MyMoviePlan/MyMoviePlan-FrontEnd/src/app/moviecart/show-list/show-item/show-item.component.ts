import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Show } from 'src/app/_models/show';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { MessengerService } from 'src/app/_services/messenger.service';
import { MoviecartService } from 'src/app/_services/moviecart.service';

@Component({
  selector: 'app-show-item',
  templateUrl: './show-item.component.html',
  styleUrls: ['./show-item.component.css']
})
export class ShowItemComponent implements OnInit {

  @Input() showItem: Show
  currentUser: UserDetails;
  //borrowed
 setNewClass: any;
 public settings: any;
 public rowLength: number;
 private reservedSeat: any[]=[] ;
 private reservedSeatDB: any[]=[] ;
 public selectingSeat: any[] = [];
 seats:any [];
 selectedTemp:any[]=[];
 private rows:any[]=[];
 private cols:any[]=[];
 noOfSeats:number=0;

 event:Event;

  constructor(
    private msg: MessengerService,
    private movecartservice: MoviecartService,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private router:Router
    ) { 


      this.currentUser = this.authenticationService.currentUserValue;

      

      this.seatStorage();
      //this.getSeats();
    }

  ngOnInit(): void {

      //remove seats
      this.movecartservice.getSeatMsg().subscribe( (seat :any) =>{
        
        this.movecartservice.removeSeat(seat);
        this.getSeats();

        this.reloadCurrentPage();
        
      });
      this.movecartservice.getClearSeatMsg().subscribe(x=>{

        if(x==="clear"){
          console.log("clear");

          this.reservedSeat=[]

          //this.selectingSeat.splice(0, this.selectingSeat.length)
          //this.getClassName(1,1);
          
        }
      })

          this.getSeatsDB(this.showItem.id);

          this.msg.getSeatMsgFromStorageDelete().subscribe(seats=>{
            //his.reservedSeat= seats;
                this.getSeats();
                //this.selectingSeat=[];

          //console.log(this.reservedSeat);
            });

            this.noOfSeats=this.showItem.seatstatus;
            //console.log(this.noOfSeats);
            //this.getRowsAndCols(this.showItem.seatstatus);

 
  }

  handleAddToCart() {

    this.msg.sendMsg(this.showItem);

  }

  userLoginFirst(){      
    
  if(this.currentUser){
   
  }
  else{
    this.router.navigate(['/login']);
    this.alertService.error("You have to Login first");
  }

};

  handleAddToCart2(seats:any) {
    
    //this.userLoginFirst();

    this.msg.getSeatMsgFromStorageAdd().subscribe(seats=>{

      //his.reservedSeat= seats;
      this.getSeats();

      //console.log(this.reservedSeat);
    });


    
        this.msg.sendSeatMsg(seats);
        this.msg.sendMsg(this.showItem);
      
   
  }


  getRowsAndCols(seats:number){
          let row=10;
          let col=seats/row;
          console.log(col);

          for(let i=0;i<row;i++){
            this.rows.push(1);
          }
          for(let j=0;j<col;j++){
            this.cols.push(1);
          }
          console.log(this.rows + "rows");
          console.log(this. cols + "cols");
  }
  
  getSeats(){
    this.reservedSeat=this.movecartservice.seats;

    }

  getSeatsDB(id:any){
    this.movecartservice.getSeatsByShowId(id).subscribe(response=>{
      
      response.forEach((item: any)=>{

        this.reservedSeatDB.push(item);
      });

    },error=>{
      console.log(error);
    });
  }

  
  seatStorage(){

    this.getRowsAndCols(this.noOfSeats);

    this.settings = {
      rows: [1, 1, 1, 1, 1,1, 1, 1, 1, 1,],
      cols: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
     // rows: this.rows,
      //cols: this.cols,
      rowCssPrefix: 'row-',
      colCssPrefix: 'col-',
      seatWidth: 10,
      seatHeight: 10,
      seatCss: 'seat',
      selectedSeatCss: 'selectedSeat',
      selectingSeatCss: 'selectingSeat'
    };
  
    let ls_settings = localStorage.getItem("settings");
   
  
    if (ls_settings == null || ls_settings == "undefined") {
      //console.log("COMing INSIDE");
      localStorage.setItem("settings", JSON.stringify(this.settings));
    } else {
      if (typeof ls_settings === 'string') {
        this.settings = JSON.parse(localStorage.getItem("settings")!);
      }
    }
  
    if (!localStorage.getItem("reservedSeat")) {
      localStorage.setItem("reservedSeat", JSON.stringify(this.reservedSeat));
      //console.log(this.reservedSeat);
    } else {
      
      this.reservedSeat = JSON.parse(localStorage.getItem("reservedSeat")!);

      console.log(JSON.parse(localStorage.getItem("reservedSeat")!));
    }
  
    this.rowLength = this.settings.rows.length;
  }
  
  
  
  getSeatNo(i: number, j: number) {
   
    return (i + j * this.rowLength + 1);
  }
  
  getClassName(i: number, j: number) {
    let id = this.settings.rowCssPrefix + i.toString() + ' ' + this.settings.colCssPrefix + j.toString();
    let className = this.settings.seatCss + ' ' + id;
    //console.log((i + j * this.rowLength + 1));
    
    if (this.reservedSeat.length > 0 && this.reservedSeat[0].indexOf(this.getSeatNo(i, j)) != -1
    || this.reservedSeatDB.length > 0 && this.reservedSeatDB.indexOf(this.getSeatNo(i, j)) != -1
    ) {
      className += (' ' + this.settings.selectedSeatCss);
    }

   // console.log(className);

    return className;
  }

  removeSelectingSeatClass(seatno: any, event: any){

   // event.target.className.replace('selectingSeat', 'seat');
    event.target.className =' seat';
  }
  reloadCurrentPage() {
    window.location.reload();
   }
  seatClick(seatno: any, event: any) {
      //this.getSeats();

      //console.log(seatno);
    
      if(this.currentUser){

                    //console.log(event.target.className);
                    
                    //console.log(event.target.className.indexOf('selectingSeat') );
              if (event.target.className.indexOf('selectedSeat') !== -1) {
                return alert('Already booked! :(');
              }
            
              if(this.selectingSeat.length+1>10){
                return alert('Not allowed to have more than 10 seats in one ticket');
              }else{
                
                        if (event.target.className.indexOf('selectingSeat') == -1) {
                          event.target.className += ' selectingSeat';
                          this.selectingSeat.push(seatno);
                        } else {
                          event.target.className.replace('selectingSeat', ' ');
                          let index = this.selectingSeat.indexOf(seatno);
                          console.log(this.selectingSeat.indexOf(seatno))
                         /* if (index > -1) {
                            this.selectingSeat.splice(index, 1);
                          }*/
                        
                        }
                    }   
                    
                    console.log(seatno);
                    console.log(this.selectingSeat);
                    console.log(this.selectingSeat.indexOf(seatno));
                    console.log(event.target.className.indexOf('selectingSeat'));

              this.handleAddToCart2(this.selectingSeat); 
              //this.selectingSeat=[];
   
      }
      else{
        
        this.alertService.error("Login first to book a move ticket");
      }

      
  }


  bookTicket() {
    if (this.selectingSeat.length == 0) {
      alert('Please select seat..!');
      return;
    }
    this.reservedSeat = this.reservedSeat.concat(this.selectingSeat);
    localStorage.setItem("reservedSeat", JSON.stringify(this.reservedSeat));
    alert('You have booked your ticket successfully');
  
  }


}
