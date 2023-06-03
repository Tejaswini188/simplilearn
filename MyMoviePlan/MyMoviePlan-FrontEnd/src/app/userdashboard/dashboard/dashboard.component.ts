import { Component, OnInit } from '@angular/core';
import { UserDetails } from 'src/app/_models/user.model';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  currentUser: UserDetails;
  userData:any;
  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService
  ) {
    this.currentUser = this.authenticationService.currentUserValue;

   }

  ngOnInit(): void {
    this.getUserData(this.currentUser.id);
  }

  getUserData(id: number){
    this.userService.getUserData(id).subscribe( data=>{
      console.log(data);

      this.userData=data;

    },(error=>{
      console.log(error.error.message);  
         
    
    })
    )

  }

}
