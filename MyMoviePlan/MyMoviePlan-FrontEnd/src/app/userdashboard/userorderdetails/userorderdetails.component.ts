import { Component, OnInit } from '@angular/core';
import { Roles, UserDetails } from 'src/app/_models/user.model';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-userorderdetails',
  templateUrl: './userorderdetails.component.html',
  styleUrls: ['./userorderdetails.component.css']
})
export class UserorderdetailsComponent implements OnInit {
  currentUser: UserDetails;
  ordersById:any;
  userRoles = Roles;
  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService
  ) {
    this.currentUser = this.authenticationService.currentUserValue;

   }

  ngOnInit(): void {
    this.getOrdersbyId(this.currentUser.id);
  }

  get isAdmin() {
    return this.currentUser && this.currentUser.roles[0] === this.userRoles.Admin ;
   }

   get isUser() {
    return this.currentUser && this.currentUser.roles[0] === this.userRoles.User ;
   } 

  getOrdersbyId(id:any){
    this.userService.getOrdersById(id).subscribe( data=>{
      //console.log(data);
      this.ordersById=data;

    },(error=>{
      console.log(error.error.message);           
    
    })
    )
  }

}
