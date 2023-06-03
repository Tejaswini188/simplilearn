import { Component, OnInit } from '@angular/core';
import { UserDetails } from 'src/app/_models/user.model';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  currentUser: UserDetails;
  userData:any;
  allusers:any;
  ordersById:any;

  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService
  ) {

    this.currentUser = this.authenticationService.currentUserValue;

    
   }

  ngOnInit(): void {

   this.getUserData(this.currentUser.id);
   //this.getOrdersbyId(this.currentUser.id);
  }


  getUserData(id: number){
    this.userService.getUserData(id).subscribe( data=>{
      //console.log(data);

      this.userData=data;

    },(error=>{
      console.log(error.error.message);  
         
    
    })
    )

  }

  getUsers(){
    this.userService.getUsers().subscribe( data=>{
      console.log(data);
      this.allusers=data;

    },(error=>{
      console.log(error.error.message);           
    
    })
    )
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
