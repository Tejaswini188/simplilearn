import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDetails } from '../_models/user.model';
import { AlertService } from '../_services/alert.service';
import { AuthenticationService } from '../_services/authentication.service';
import { MoviecartService } from '../_services/moviecart.service';

@Component({
  selector: 'app-reviewpayment',
  templateUrl: './reviewpayment.component.html',
  styleUrls: ['./reviewpayment.component.css']
})
export class ReviewpaymentComponent implements OnInit {
  currentUser: UserDetails;
  paymentId: string;
  token: string;
  PayerId: string;
  url:any;
  paymentDetails:any;
  paymentResponse:any;
  clicked=false;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private movecartservice: MoviecartService

   
  ) {
    this.currentUser = this.authenticationService.currentUserValue;

   }

  ngOnInit(): void {   
        this.getPayPalURLParams();     
  }

   getPayPalURLParams() {
    let url = window.location.href;
    let parts = url.split("/");
    let firstUrlTerm = parts[3];
    let secondURlTerm=firstUrlTerm.split("?");
    let thirdUrlTerm=secondURlTerm[1];
    let firstTermParts = thirdUrlTerm.split("&");
    this.paymentId=firstTermParts[0].split("=")[1];
    this.token=firstTermParts[1].split("=")[1];
    this.PayerId=firstTermParts[2].split("=")[1]; 
    console.log(this.paymentId);
    console.log(this.token);
    console.log(this.PayerId);

    this.reviewPayment(this.paymentId,this.token,this.PayerId);
     
 }
          /* getUrlVars() {
              let vars: { [x: string]: any; };
              let parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,  (key, value): any=> {
                  vars[key] = value;

                  console.log(vars);
              });            
              
          }*/ 


 reviewPayment(paymentid:any,token:any, payerid:any){

  this.movecartservice.reviewPayment(paymentid,token,payerid).subscribe(response=>{
     console.log(response);
     this.paymentDetails=response;
  },error=>{
       console.log(error);
  });
 }

 executePayment(paymentid:any,token:any, payerid:any){


  this.movecartservice.executePayment(paymentid,token,payerid).subscribe(response=>{
    
    this.paymentResponse= response;
        

    this.router.navigate(['/receiptpayment',paymentid,token, payerid]);

    this.alertService.success("Payment Successfully Done");
    
 },error=>{
      console.log(error.message);
      this.alertService.error(error.error.message);
 });
 }

}
