import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDetails } from '../_models/user.model';
import { AlertService } from '../_services/alert.service';
import { AuthenticationService } from '../_services/authentication.service';
import { MoviecartService } from '../_services/moviecart.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  clicked = false;
  currentUser: UserDetails;
  items:any [];
  seats:any[];
  seatsNumbers:string;
  cartTotal = 0;
  orderDetails:any[]=[];
  seatDetails:any[]=[];
  orderObj: any={};
  seatObj:any={};
  payObj: any={};
  orderstatus:boolean=false;
  savedOrderDetails:any;
  
   

  constructor(
    private authenticationService: AuthenticationService,
    private movecartservice: MoviecartService,
    private alertService:AlertService,
    private router:Router) { 

    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit(): void {

    this.movecartservice.getItemMsg().subscribe( (cartitems :any) =>{
      this.movecartservice.remove(cartitems);
      
    });

    //remove seats
    this.movecartservice.getSeatMsg().subscribe( (seat :any) =>{
      this.movecartservice.removeSeat(seat);
     
    });
    
    this.getitems();
    this.getseats();
    
  }


  getitems(){
    console.log(this.movecartservice.items);
    this.items=this.movecartservice.items;
    this.cartTotal = 0
    this.items.forEach(item => {
    this.cartTotal += (item.qty * item.price)  ;
    });
}


  getOrders(){
    
    let totalitems=0;
    let productnameitems='';
    this.items=this.movecartservice.items;      
    this.items.forEach(item => {
        
      this.orderObj={
        customerId:this.currentUser.id,
        productName:item.productName,
        price:item.price,
        quantity:item.qty,
        seatnumber:this.seatsNumbers,
        productId:item.productId,
        shipping:0,
        tax:0,
        subtotal:item.qty * item.price,
        total:item.qty * item.price
      }
    

      totalitems+=item.qty * item.price;
      productnameitems=productnameitems+item.productName +"_";

      
      this.orderDetails.push(this.orderObj);    
          
           });

            console.log(this.payObj);  
  }


  getseats(){
    this.seats=this.movecartservice.seats; 
       
    this.seatsNumbers=this.seats.toString();
    console.log(this.seatsNumbers);
    
  }

  saveSeats(){
    this.seats=this.movecartservice.seats; 
    let item =this.movecartservice.items
    let i=0;
    let productId=0;
    this.items.forEach(item => {
      productId=item.productId
    });

    console.log(this.seats[0]);

      this.seats[0].forEach((seat: any)=>{
      console.log(i);
      this.seatObj={
        customerId:this.currentUser.id,
        seatnumber:seat,
        showId: productId    
      }
      
      this.seatDetails.push(this.seatObj);
    });

    console.log(this.seatDetails);
    console.log(this.seatDetails.length);

    for(let i=0; i<this.seatDetails.length;i++){

      this.movecartservice.saveSeats(this.seatDetails[i]).subscribe(response=>{

           console.log(response);
      },error=>{
        console.log(error.error.message);
      });
    }  

  }

  getPayDetails(){
    let totalitems=0;
    let productnameitems='';
    this.items=this.movecartservice.items;      
    this.items.forEach(item => {  
      totalitems+=item.qty * item.price;
      productnameitems=productnameitems+item.productName +"_"+this.savedOrderDetails.id;              
           });

        this.payObj={
            productName:productnameitems,
            shipping:0,
            tax:0,
            subtotal:totalitems,
            total:totalitems
          }
            console.log(this.payObj);  
           
  }


  paypalPayment(){    

      this.saveOrders(); 
      //this.saveSeats();
      
   
  }

  makePayment(){

        if(this.payObj){
        this.movecartservice.makePayment(this.payObj).subscribe(response=>{
          
          console.log(response.message)

          localStorage.removeItem('items') 
          localStorage.removeItem('reservedSeat') 
          window.location.href = response.message;         
          
          
        },error=>{  
          //this.alertService.error(error.error);
          console.error(error)
          
        }
        );   
      }
  }

  saveOrders(){

     this.getOrders();

      for(let i=0; i<this.orderDetails.length;i++){
      this.movecartservice.saveOrders(this.orderDetails[i]).subscribe(response=>{
       

        this.savedOrderDetails=response;

       
        console.log(this.savedOrderDetails);
        if(this.savedOrderDetails){ 
          this.getPayDetails();
          this.makePayment();
        }      
              
        this.orderstatus=true;
        this.saveSeats();
      
       
      },error=>{  
        this.alertService.error(error.error);
        this.orderstatus=false;
      }
      );
    }  
  
    
  }


}
