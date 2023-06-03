import { Component, OnInit } from '@angular/core';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-customerorders',
  templateUrl: './customerorders.component.html',
  styleUrls: ['./customerorders.component.css']
})
export class CustomerordersComponent implements OnInit {

  currentUser: UserDetails;
  orders:any;

  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private alert: AlertService

  ) { 

    this.currentUser = this.authenticationService.currentUserValue;

  }

  ngOnInit(): void {

    this.getOrders();
  }
  getOrders(){
    this.userService.getOrders().subscribe( data=>{
      console.log(data);
      this.orders=data;

    },(error=>{
      console.log(error.error.message);           
    
    })
    )

  }

}
