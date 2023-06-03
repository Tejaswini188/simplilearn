import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Roles, User, UserDetails } from './_models/user.model';
import { AuthenticationService } from './_services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'NMS_Cinemas_App';

  currentUser: UserDetails;
  userRoles = Roles;

  constructor(
      private router: Router,
      private authenticationService: AuthenticationService
  ) {
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);

     //console.log(this.currentUser.roles[0]) ; 
  }


  get isAdmin() {
    return this.currentUser && this.currentUser.roles[0] === this.userRoles.Admin ;
   }

   get isUser() {
    return this.currentUser && this.currentUser.roles[0] === this.userRoles.User ;
   } 
  logout() {
      this.authenticationService.logout();
      this.router.navigate(['/home']);
  }
}
