import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDetails } from '../_models/user.model';
import { AlertService } from '../_services/alert.service';
import { AuthenticationService } from '../_services/authentication.service';
import { MoviecartService } from '../_services/moviecart.service';

@Component({
  selector: 'app-paymentreceipt',
  templateUrl: './paymentreceipt.component.html',
  styleUrls: ['./paymentreceipt.component.css']
})
export class PaymentreceiptComponent implements OnInit {

  currentUser: UserDetails;
  paymentDetails:any;
  paymentId:any;
  PayerId:any;
  token:any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private movecartservice: MoviecartService
  ) {

    this.currentUser = this.authenticationService.currentUserValue;

   }

  ngOnInit(): void {

       this.route.params.subscribe(params => {
       this.paymentId= params['paymentId']; 
       this.PayerId=params['PayerId']; 

      this.receiptPayment(this.paymentId,this.token,this.PayerId);
                    
        }); 
        console.log(`${this.paymentId}${this.PayerId} `);


  }

  receiptPayment(paymentid:any,token:any ,payerid:any){

    this.movecartservice.reviewPayment(paymentid, token,payerid).subscribe(response=>{
       console.log(response);
       this.paymentDetails=response;
    },error=>{
         console.log(error);
    });
   }

}
