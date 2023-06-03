import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Roles, UserDetails } from '../_models/user.model';
import { AlertService } from '../_services/alert.service';
import { AuthenticationService } from '../_services/authentication.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-paymentdetails',
  templateUrl: './paymentdetails.component.html',
  styleUrls: ['./paymentdetails.component.css']
})
export class PaymentdetailsComponent implements OnInit {

  currentUser: UserDetails;
  userRoles = Roles;
  paymentByOrderId:any;
  orderId:any;

  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private route:ActivatedRoute,
    private alert:AlertService
  ) { 
    this.currentUser = this.authenticationService.currentUserValue;

  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
            this.orderId = params['id']; 
            console.log(this.orderId);      
            this.getPaymentByOrderId(this.orderId);                    
        });

  


  }

  get isAdmin() {
    return this.currentUser && this.currentUser.roles[0] === this.userRoles.Admin ;
   }

   get isUser() {
    return this.currentUser && this.currentUser.roles[0] === this.userRoles.User ;
   } 
  
  getPaymentByOrderId(id:any){
    this.userService.getPayDetailsByOrderID(id).subscribe( data=>{
      console.log(data);
      this.paymentByOrderId=data;

      if(data==null){
        this.alert.error("There is no Payment Details!!")

      }

    },(error=>{
      console.log(error.error.message);           
    
    })
    )
  }

}
