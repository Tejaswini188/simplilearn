import { Component, OnInit } from '@angular/core';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-customerpaydetails',
  templateUrl: './customerpaydetails.component.html',
  styleUrls: ['./customerpaydetails.component.css']
})
export class CustomerpaydetailsComponent implements OnInit {

  currentUser: UserDetails;
  paymentsData:any;
  
  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private alert: AlertService
  ) {
    this.currentUser = this.authenticationService.currentUserValue;

  }

  ngOnInit(): void {
    this.getPaymentDetails();
  }

  getPaymentDetails(){

    this.userService.getPayDetails().subscribe( data=>{
      console.log(data);
      this.paymentsData=data;

    },(error=>{
      console.log(error.error.message);           
    
    })
    )
  }

}
