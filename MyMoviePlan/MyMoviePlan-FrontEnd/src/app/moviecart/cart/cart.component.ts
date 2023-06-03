import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { MessengerService } from 'src/app/_services/messenger.service';
import { MoviecartService } from 'src/app/_services/moviecart.service';



@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

 
  currentUser: UserDetails;
  items:any [];
  seats:any[];
  seatsTemp:any[]=[];
  itemsadded:any;
  cartTotal = 0;
  private reservedSeat: any[] = [];
  selectingSeat:any;
  selectSeats:boolean=true;

  constructor(private msg: MessengerService,
    private movecartservice: MoviecartService,
    private router:Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService
    ) { 

      this.currentUser = this.authenticationService.currentUserValue;
      this.getitems();
      this.getSeats();
      //console.log(this.seats);
    }

  ngOnInit(): void {
    this.msg.getMsg().subscribe((show: any) => {
  
      console.log(this.selectSeats)
      if(this.selectSeats){
     this.addProductToCart(show);   
     }

    }); 

    this.msg.getSeatMsg().subscribe((seat: any) => {
  //add seats
     /*promblem statement: if it exists do not add*/

     this.seats.indexOf(seat)
     console.log(this.seats+"  reserved seats"); 
     
     
     console.log(this.seats.indexOf(seat)+" seats comparison");

     if(this.seats.indexOf(seat)==-1){
      this.movecartservice.removeSeat(seat);
      this.movecartservice.addSeats(seat);
      console.log(this.seatsTemp+ " temporary seats");
      console.log(seat+" selected seat");
     }     
     }); 



     this.movecartservice.getItemMsg().subscribe( (cartitems :any) =>{
      this.movecartservice.remove(cartitems);
      this.getitems();
    });

    //remove seats
    this.movecartservice.getSeatMsg().subscribe( (seat :any) =>{
    this.movecartservice.removeSeat(seat);

    });


    this.getSeats();
    
  }

  
  addProductToCart(show: any) {

    if(this.currentUser){

    }
    else{

      this.router.navigate(['/login']);
      this.alertService.error("You have to Login first");
    }
 
    let productExists = false;

    for (let i in this.items) {
      if (this.items[i].productId === show.id) {
        this.items[i].qty++
        productExists = true
        break;
      }
    }

    if (!productExists) {
      this.items.push({
        productId: show.id,
        productName: show.movie.moviename,
        qty: 1,
        price: show.price
      })
       }
  
    this.cartTotal = 0
    this.items.forEach(item => {
      this.cartTotal += (item.qty * item.price)
       this.movecartservice.remove(item);     
      });

      this.items.forEach(item => {
 
        this.movecartservice.add(item);
       
        });

  
  }

  getitems(){
      this.items=this.movecartservice.items;
      this.cartTotal = 0
      this.items.forEach(item => {
      this.cartTotal += (item.qty * item.price)
          
      });
  }

  getSeats(){
    this.seats=JSON.parse(localStorage.getItem('reservedSeat') ||'[]');
    console.log(this.seats);
  }


  removeItemsmsg(){
    
      this.movecartservice.sendItemMsg(this.items);
      this.movecartservice.sendSeatMsg(this.seats);
      this.movecartservice.sendClearSeatMsg('clear');
       

  }

  checkout(){
    this.router.navigate(['/payment']);
  }
  

}
