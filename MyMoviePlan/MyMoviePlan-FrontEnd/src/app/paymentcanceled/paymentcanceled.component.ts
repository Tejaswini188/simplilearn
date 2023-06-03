import { Component, OnInit } from '@angular/core';
import { UserDetails } from '../_models/user.model';
import { AlertService } from '../_services/alert.service';
import { AuthenticationService } from '../_services/authentication.service';

@Component({
  selector: 'app-paymentcanceled',
  templateUrl: './paymentcanceled.component.html',
  styleUrls: ['./paymentcanceled.component.css']
})
export class PaymentcanceledComponent implements OnInit {
 
  currentUser: UserDetails;
  constructor( 
     private alertService: AlertService,
     private authenticationService: AuthenticationService
     ) {
    this.currentUser = this.authenticationService.currentUserValue;

   }

  ngOnInit(): void {
  }

}
