import { Component, Input, OnInit } from '@angular/core';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {
  @Input() cartItem: any
  currentUser: UserDetails;
 
  constructor(
    private authenticationService: AuthenticationService,
    private alertService: AlertService

  ) { 
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit(): void {
  }

}
